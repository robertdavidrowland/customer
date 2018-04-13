# Customer Demo UI

## Requirements

Provide an application that allows a company to see their customer information.

Customers have the following information associated with them:

- Unique customer identifier.
- Status: one of "prospective", "current" or "non-active".
- Creation date and time.
- General information like name and contact details.

The company can also make notes for each customer. A customer can have any number of notes associated with them.

The user should be able to:

- Filter and sort the list of customers.
- Click on a customer in the list to view their details and add/edit notes for that customer.
- Change their status.

Notes

1. Construct a full-stack application with a browser user interface and data store.
2. You may use any programming languages, data store and frameworks you want.
3. A user interface for editing customer details other than their status and the notes is not required.
4. Think about all of the supporting code you may need for a professionally-built application.

## Implementation details

This has been set up as Spring Boot MVC + Hibernate.  The back end provides a REST API to get/add/update/delete customers and thier notes.

The front end is a single page application found in /resource/static/index.html.  This uses JQuery and 3rd party open source table renderer Data Tables (https://datatables.net/).

The application is built with Maven.

## Build instructions

```
$ mvn clean package
$ java -jar target/customer-0.0.1-SNAPSHOT.jar
```

The application will be availble on http://localhost:8080

## Known Problems and Notes

Data tables is available under MIT licence which may not be acceptable to corporate clients.
The front end uses very verbose JQuery - should consider modern framework i.e. React.
Should check encoding post data - application is untested with special characters.
Better error handling generally.
More tests.
Mismanagement of underlying data table representation vs what is shown on browser/send to back end - this results in bugs around new notes:
- cannot delete new notes until browser is refreshed.
- saving status causing new notes to be deleted unless browser is refreshed first.
Fix date and status formatting.
Ensure when editing data the focus move to the form fields.
