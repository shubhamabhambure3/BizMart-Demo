# BizMart - Project Master Tracker

## Project Information

### Project Name

BizMart - Business Valuation & Acquisition Marketplace

### Project Type

Startup MVP

### Goal

A platform where business owners can list companies for acquisition or investment, and buyers/investors can discover and connect with them.

### MVP Scope

* Authentication & Authorization
* Seller Profile
* Company Management
* Financial Information
* Business Valuation
* Business Listing
* Buyer Profile
* Interest Management
* Basic Matching Engine
* Admin Dashboard

---

# Technology Stack

## Backend

* Java 21
* Spring Boot
* Spring Security
* JWT
* Spring Data JPA
* Hibernate
* MySQL
* Maven

## Frontend

* React
* Bootstrap 5
* Axios

## Tools

* STS
* MySQL Workbench
* Postman
* VS Code

---

# Architecture Decisions

## Decision 001

Use JWT Authentication instead of Session Authentication.

Reason:
Industry standard and React-friendly.

Status:
Approved

---

## Decision 002

Use React Frontend and Spring Boot REST APIs.

Reason:
Portfolio and industry relevance.

Status:
Approved

---

# Database Tables

## Planned

* users
* roles
* companies
* company_financials
* valuations
* listings
* buyer_profiles
* interests

---

# Milestone Tracking

## Milestone 0 - Project Setup

Status:
COMPLETED

Tasks:

- [x] Create Spring Boot Project
- [x] Configure MySQL
- [x] Verify Application Startup
- [x] Create Base Package Structure

Notes:

None

---

## Milestone 1 - Authentication

Status:
COMPLETED

Tasks:
* [x] Create Package Structure
* [x] User Entity
* [x] Role Enum
* [x] User Repository
* [x] Registration DTO
* [x] AuthService
* [x] AuthController
* [x] Registration API
* [x] Password Encryption (BCrypt)
* [x] Login API
* [x] Global Exception Handler
* [x] JWT Generation
* [x] JWT Extraction
* [x] JWT Validation
* [x] Spring Security Configuration
* [x] Role-based JWT

---

## Milestone 2 - Company Management Module

Status:
COMPLETED

Tasks:

* [x] Company Entity
* [x] Company Repository
* [x] Company DTO
* [x] Company Service
* [x] Company Controller
* [x] Create Company API
* [x] Get Company API
* [x] Get All Companies API
* [x] Update Company API
* [x] Delete Company API
* [x] Seller Dashboard API

## Class Summary

### CompanyService

createCompany()
- Create and save company

getAllCompanies()
- Fetch all companies

getCompanyById()
- Fetch company by id

mapToResponse()
- Convert Entity to DTO

updateCompany()
- Update company

deleteCompany()
- Delete company

getCompaniesByOwnerId()
- Fetch seller companies

---

## Milestone 3 - Financial Module

Status:
COMPLETED

Tasks:

* [x] Financial Entity
* [x] Financial CRUD
* [x] Valuation Engine

---

### ValuationService

generateValuation()
- Generate valuation from financial data

getValuationByCompanyId()
- Fetch valuation by company id

mapToResponse()
- Convert Entity to DTO

---

## Milestone 3.5 - Quality Pipeline

* [x] JUnit Setup
* [x] GitHub Actions CI Pipeline

* [x] BizmartBackendApplicationTests
* [x] CompanyServiceTest
* [x] FinancialServiceTest
* [x] ListingServiceTest
* [x] BuyerProfileServiceTest
* [x] InterestServiceTest
* [x] MatchingServiceTest

* [ ] AuthService Tests
* [ ] MockMvc API Tests

---
## Class Summary

### FinancialService

createFinancial()
- Create and save financial record

getAllFinancials()
- Fetch all financial records

getFinancialById()
- Fetch financial record by id

getFinancialByCompanyId()
- Fetch financial record by company id

updateFinancial()
- Update financial record

deleteFinancial()
- Delete financial record

mapToResponse()
- Convert Entity to DTO
---

## Milestone 4 - Listing Module

Status:
COMPLETED

Tasks:

Listing Module

* [x] Listing Entity
* [x] Listing Repository
* [x] Listing DTO
* [x] Listing Service
* [x] Listing Controller

* [x] Create Listing API
* [x] Get Listing API
* [x] Get All Listings API
* [x] Get Listings By Company API
* [x] Update Listing API
* [x] Delete Listing API

* [x] Listing Module Tested

---

## Milestone 5 - Buyer Module

Status:
COMPLETE

Tasks:

* [x] Buyer Profile Entity
* [x] Buyer Profile Repository
* [x] Buyer Profile DTO
* [x] Buyer Profile Service
* [x] Buyer Profile Controller

* [x] Create Buyer Profile API
* [x] Get Buyer Profile API
* [x] Get All Buyer Profiles API
* [x] Get Buyer Profile By User API
* [x] Update Buyer Profile API
* [x] Delete Buyer Profile API

* [x] Buyer Profile Module Tested

* [x] Interest API
* [x] Matching Engine

---

### BuyerProfileService

createBuyerProfile()
- Create and save buyer profile

getAllBuyerProfiles()
- Fetch all buyer profiles

getBuyerProfileById()
- Fetch buyer profile by id

getBuyerProfileByUserId()
- Fetch buyer profile by user id

updateBuyerProfile()
- Update buyer profile

deleteBuyerProfile()
- Delete buyer profile

mapToResponse()
- Convert Entity to DTO

### InterestService

createInterest()
- Create buyer interest

getAllInterests()
- Fetch all interests

getInterestById()
- Fetch interest by id

getInterestsByBuyerProfileId()
- Fetch buyer interests

getInterestsByListingId()
- Fetch listing interests

updateInterest()
- Update interest status/details

deleteInterest()
- Delete interest

mapToResponse()
- Convert Entity to DTO

---

## Milestone 6 - React Frontend

Status:
IN PROGRESS

Tasks:

* [ ] Dashboard

* [x] React + Vite Setup
* [x] Bootstrap 5 Integration
* [x] React Router Configuration

Authentication Module

* [x] Login Page
* [x] Login API Integration
* [x] JWT Token Storage
* [x] Protected Routes
* [x] Logout Functionality

Registration Module

* [x] Registration Page
* [x] Registration API Integration

Navigation

* [x] Navbar Component
* [x] Dashboard Navigation
* [x] Companies Navigation

Company Management UI

* [x] Companies Page
* [x] Company Creation Form
* [x] Company Service Layer
* [x] Axios JWT Interceptor
* [x] Company Creation API Integration
* [x] Company Persistence Validation
* [ ] Owner-specific Company Listing
* [ ] Company Update UI
* [ ] Company Delete UI

Pending

* [ ] Listings Page
* [ ] Financials Page
* [ ] Buyer Module UI
* [ ] Matching UI
* [ ] Admin Dashboard UI
* [ ] UI Refinement


---

## Milestone 7 - Security Hardening

Status:
COMPLETED

* [x] Derive owner from JWT
* [x] Restrict company creation to SELLER
* [x] Restrict company update to owner
* [x] Restrict company deletion to owner
* [x] Add ownership validation

---

## Future Improvements

- Replace ownerId with User entity relationship (@ManyToOne)
- Derive owner from JWT instead of request body
- Enforce SELLER role for company creation
- Add foreign key between companies.owner_id and users.id
- Implement role-based method security - Advanced Security Improvements
- Add UserDetailsService - Advanced Security Improvements
- Add foreign key constraints - Advanced Security Improvements

---

# Completed APIs

Authentication Module

POST /api/auth/register
POST /api/auth/login

Company Management Module

POST   /api/companies
GET    /api/companies
GET    /api/companies/{id}
PUT    /api/companies/{id}
DELETE /api/companies/{id}

GET    /api/companies/owner/{ownerId}

Financial Module

POST   /api/financials
GET    /api/financials
GET    /api/financials/{id}
GET    /api/financials/company/{companyId}
PUT    /api/financials/{id}
DELETE /api/financials/{id}

Valuation Module

POST /api/valuations
GET  /api/valuations/company/{companyId}

Listing Module

POST /api/listings
GET /api/listings
GET /api/listings/{id}
GET /api/listings/company/{companyId}
PUT /api/listings/{id}
DELETE /api/listings/{id}

Buyer Profile Module

POST   /api/buyers
GET    /api/buyers
GET    /api/buyers/{id}
GET    /api/buyers/user/{userId}
PUT    /api/buyers/{id}
DELETE /api/buyers/{id}

Interest Module

POST   /api/interests
GET    /api/interests
GET    /api/interests/{id}
GET    /api/interests/buyer/{buyerProfileId}
GET    /api/interests/listing/{listingId}
PUT    /api/interests/{id}
DELETE /api/interests/{id}

Matching Module

GET /api/matching/{buyerProfileId}

---

# Pending APIs

NA

---

# Known Issues

* Company listing currently displays all companies using GET /api/companies
* Owner-specific company visibility requires redesign
* UI refinement intentionally deferred until MVP functionality completion

---

# Current Status

Current Milestone:
Milestone 6 - React Frontend MVP

Current Task:
Companies Module Completion

Next Task:
Owner-specific Company Listing

# Git History

## Commit 001

Message:
Initial authentication module setup

Features:

* Spring Boot Setup
* MySQL Configuration
* Package Structure
* User Entity
* Role Enum
* User Repository
* Registration API
* BCrypt Encryption
* Login API

# Git Information

Repository:
https://github.com/shubhamabhambure3/bizmart-platform

Branch:
* feature/frontend-setup
  feature/security-hardening
  main

Last Commit:
feat(company): implement company creation workflow

## Project Handover Notes

### Completed Modules

1. Authentication Module

   * Registration
   * Login
   * JWT Authentication
   * BCrypt Password Encoding
   * Spring Security
   * Role-Based JWT Claims

2. Company Management Module

   * Company CRUD APIs
   * Seller Dashboard API

3. Financial Module

   * Financial CRUD APIs

4. Valuation Module

   * Business Valuation Generation
   * Valuation Retrieval APIs

5. Listing Module

   * Listing CRUD APIs
   * Listing Status Management

6. Quality Pipeline

   * JUnit Setup
   * CompanyServiceTest
   * FinancialServiceTest
   * ListingServiceTest
   * H2 Test Configuration
   * GitHub Actions CI Pipeline


### Current Database Tables

users
companies
company_financials
valuations
listings


### Security Hardening Backlog

- Derive owner from JWT
- Restrict company creation to SELLER
- Restrict company update to owner
- Restrict company deletion to owner
- Add UserDetailsService
- Add ownership validation
- Add foreign key constraints

### Important Decisions

- Use explicit loops instead of Streams for readability
- Use Optional.isEmpty() instead of orElseThrow()
- Follow Controller -> Service -> Repository architecture
- Commit after completing a feature/module

Date:
2026-06-06