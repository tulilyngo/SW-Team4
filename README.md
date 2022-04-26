# SW-Team4
# Trivia Game : A two-player quiz game
[read_me.txt](https://github.com/tulilyngo/SW-Team4/files/8560289/read_me.txt)


Installation Guide
[Database Setup]
    Download and install XAMPP.
    https://www.apachefriends.org/download.html
    Start XAMPP MySql Module
    Cmd terminal enter: cd c:\xampp\mysql\bin
    mysql -h localhost -u student -p
    Enter password
    Access database file

[Connect with Database]
    Download and install MySQL Connector/J 
    https://dev.mysql.com/downloads/connector/j/ 
    Connect with Database using ‘properties file’
    Provide database URL, username, and password
    ei: con = DriverManager.getConnection ("jdbc:mysql://localhost:3306/student_space","student","hello");

[Project Setup] (how to clone the project to user’s editor of their choice)
    Select File button
    Select import 
    Git folder →  Project from Git (smart import)
    Clone URI
    Enter link -> will auto fill some of the fields
    Select protocol -> used https
    Enter username from github
    Create user token in github 
    Copy/Save token
    Enter token as password 
    Select master branch
    Select location for project
    Import projects from github
    Package Explorer will have project showing
    Git staging 
    window → show view → other → git → git staging
    Git repository window
    window → show view → other → git → git Repository


Running the Application Guide
    Install .bat or .sh file above project
    Use cmd.exe to navigate to location of .bat or .sh file
    Enter file name and execute
