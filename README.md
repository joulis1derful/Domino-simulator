# Domino-simulator
Mini web-application 'Domino'
Application consists of 3 pages:
1) Bars set generation (amount configured by the constant variable), clicking the button 'Start' set of userbars stores into the database and redirects you onto the next page. Also there is a possibility to re-roll your set of bars by clicking the button 'Re-roll'. This page is available by address "/generate";
2) Pulling out current set from database and making a chain of it. You may make the chain by 2 ways: either manually by entering the number of element from proposed list and clicking 'Upload chain' or clicking the button 'Make the longest chain'. Every chain stores into the database and displays in 'History' block on the same page. Page is available by address "/results";
3) Main page from which you may get to any of 2 pages that were mentioned above.
Client DB - MySQL, if gonna use another client - replace configuration in db.properties file.
To start project: 
mvn clean compile package jetty:run
Web-app with 2 pages - bars generation and chain generation with chain history
