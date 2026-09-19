# Trainer–Client Management System

A Java application for managing personal trainers, their clients, training sessions, licenses and receipts. 
The project was built as a university software engineering assignment to demonstrate a clean **multi-tier architecture** using classic design patterns (Template Method, Singleton) rather than a framework.

## Overview

The system supports a personal-training business workflow:

- Trainers log in and can be marked online/offline.
- Trainers hold one or more **licenses**, each tied to an issue date.
- Clients (**Korisnik**) are registered, searched, edited and removed.
- Trainers run **training sessions** (**Trening**) of different **training types** (**VrstaTreninga**).
- After a session, a trainer issues a **receipt** (**Priznanica**) to a client, made up of individual **receipt items** (**StavkaPriznanice**), each linked to a specific training.

## Key Features

- Trainer login / online status
- Add, search, update and delete clients
- Issue and retrieve receipts for clients
- Add and retrieve trainer licenses
- Retrieve all training sessions and training types
- Server-side validation on every write operation before it reaches the database

## Architecture

The application follows a strict **3-tier / layered architecture**, so that each layer only talks to the one directly below it:

```
Client Application (GUI)
        |
Network Layer  (client ↔ server communication)*
        |
ServerController                (routes each request to the right operation)
        |
System Objects — package `so`   (AbstractSO — Template Method)
        |
Domain Objects — package `domm` (AbstractDomainObject)
        |
DBBroker  (Singleton, JDBC)
        |
Database*
```
`*` Networking is assumed to be socket-based (`ObjectInputStream` / `ObjectOutputStream`); the database is assumed to be a relational database accessed via JDBC.

### Design patterns

| Pattern | Where | Purpose |
|---|---|---|
| **Template Method** | `AbstractSO.templateExecute()` | Fixes the sequence `validate() → execute() → commit()/rollback()` for every system operation, while subclasses only implement the two abstract steps. |
| **Singleton** | `DBBroker` | Guarantees a single, shared database connection/broker instance across the server. |
| **Facade** | `ServerController` | Gives the client a single entry point into the server-side logic, hiding the system-object layer behind it. |

### System operations (`so` package)
Each system operation extends `AbstractSO` and implements `validate()` / `execute()`:

| Package | Operations |
|---|---|
| `so.trener` | `SOLogin`, `SOSetOnline` |
| `so.korisnik` | `SOSacuvajNovogKorisnika`, `SOIzmeniKorisnika`, `SOObrisiKorisnika`, `SOPretraziKorisnike`, `SONadjiKorisnika` |
| `so.priznanica` | `SOAddPriznanica`, `SOGetPriznanica`, `SOUpdatePriznanica` |
| `so.licenca` | `SODodajLicencu`, `SOGetLicence` |
| `so.trening` | `SOGetAllTrening` |
| `so.vrstatreninga` | `SOUcitajVrsteTreninga` |

Every operation is invoked by the server through the same contract:
```java
sc:ServerController → aso:SOxxx.templateExecute(domainObject)
                          → validate(domainObject)
                          → insert / update / delete / select via dbb:DBBroker
                       ← signal (+ result, where applicable)
```

## Domain Model (`domm` package)

All domain classes extend the abstract `AbstractDomainObject`, which defines how an object maps to its database table (`tableName()`, `columnsForInsert()`, `requirementForSelect()`, etc.).

- **Trener** — id, ime, prezime, email, sifra, online
- **Korisnik** — idKorisnik, ime, prez, email, vrstaTreninga
- **Licenca** — id, naziv, opis
- **TrenerLicenca** — link between a `Trener` and a `Licenca`, with an issue date
- **Trening** — id, tip, trajanje, cena
- **VrstaTreninga** — id, opis
- **Priznanica** — idPriznanica, ukupanIznos, mestoIzdavanja, datumIzdavanja, trener, korisnik, stavke
- **StavkaPriznanice** — id, rb, brTreninga, iznos, vremePocetka, vremeZavrsetka, napomena, trening

**Relationships**

- `Trener` 1 — * `TrenerLicenca` * — 1 `Licenca`
- `Trener` 1 — * `Priznanica` * — 1 `Korisnik`
- `Priznanica` 1 ◆— * `StavkaPriznanice` (composition)
- `Trening` 1 — * `StavkaPriznanice`
- `VrstaTreninga` 1 — * `Korisnik`

<img width="1143" height="743" alt="dijKlasaSaEnum" src="https://github.com/user-attachments/assets/3ff1f7e4-7d8e-482c-93e2-2175a3a188b2" />

