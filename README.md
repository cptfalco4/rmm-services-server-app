# RMM Services Server App
### author: Nicole Falco
In order to run the application, you will need to install Postgres and have a local instance running. See the following link:  

https://www.postgresqltutorial.com/install-postgresql/

Set the username to the Database you create locally to "postgres" and the password to "password".  
OR, you can create your own user and password and simply update it in the application.properties file ([GitHub Pages]https://github.com/cptfalco4/rmm-services-server-app/tree/main/src/main/resources/application.properties)  
Here you can also update the Postgres server info if you do not wish to use localhost

Now, simply run the application.

In order for the prices to be dynamic, I have created them as a table. If you wish to have the same services and prices available as indicated in the problem statement, run the following commands on your Postgres database:  
(Sub "rmmservicesdb.public" for your own database path if not the same.)

INSERT INTO rmmservicesdb.public.service_cost(service_name, price) VALUES ('Antivirus Mac', 57;  
INSERT INTO rmmservicesdb.public.service_cost(service_name, price) VALUES ('Antivirus Windows', 5);  
INSERT INTO rmmservicesdb.public.service_cost(service_name, price) VALUES ('Cloudberry', 3);  
INSERT INTO rmmservicesdb.public.service_cost(service_name, price) VALUES ('PSA', 2);  
INSERT INTO rmmservicesdb.public.service_cost(service_name, price) VALUES ('TeamViewer', 1);  

For now, there is only one user for BasicAuth.  
username: admin  
password: password  
Make sure this is included as BasicAuth header in all requests. 

