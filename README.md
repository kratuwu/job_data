Backend Exercise : Expose an API for querying job data

The goal of this exercise is to design a read-only API (REST) that returns one or more records from static set of job data.

User Story: As a developer I want to

list job data via API GET request

Filter by one or more fields/attributes (e.g. /job_data?salary[gte]=120000) (Show only filtered row. Expected filter able column: job title, salary, gender )
Filter by a sparse fields/attributes (e.g. /job_data?fields=job_title,gender,salary) (Show only filtered column)
Sort by one or more fields/attributes (e.g. /job_data?sort=job_title&sort_type=DESC)
A few quick notes on submitting the Backend Exercise 

Don't worry about any web application concerns other than serializing JSON and returning via a GET request.
Feel free to design the URL structure and JSON schema that you believe creates the best client/consumer experience. We want to see how you initiate a new project, project structure and design. 
