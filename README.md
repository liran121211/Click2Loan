
<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="https://github.com/othneildrew/Best-README-Template">
<img src="https://i.ibb.co/SvKYxZ0/facebook-cover-photo-1.png" alt="facebook-cover-photo-1" border="0" width="377" height="143">
  </a>
  
  
  <u><h3 align="center">Click2Loan</h3></u>

  <h4 align="center">
A simple and easy way to get a loan without leaving home
    <h4 />

<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#screenshots">Screenshots</a></li>
    <li><a href="#features">Features</a></li>
    <li><a href="#limitations">Limitations</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgements">Acknowledgements</a></li>
  </ol>
</details>
    
    <!-- ABOUT THE PROJECT -->
## About The Project

As part of our software project management course, we were asked to think of an idea for an app in the financial field. We were thinking of an app that would provide a solution for lending, but we wanted to add something a little different to it. We have integrated into the system a neural network that according to information collected in a database, you can make a decision whether the customer applying for a loan will be eligible or not.

The system comes to solve a problem where the human factor can not always make the right decision whether the customer is eligible for a loan or not, so the system will receive real-time information from the customer and within a few minutes a decision is made whether the loan was approved or not.

The main purpose of the system is to give a loan to those who are conveniently eligible without a long wait or banker involvement in every decision.
    
With the service of [DigitalOcean](https://m.do.co/c/70ab03cd54f1) We managed to fully operate PostgreSQL database that performs operations according to the command it receives from the client.
    
The whole system was built in Java so we wanted to use the latest design libraries available to the user.
The entire graphical user interface was built using JavaFX. 
    
**Here's why we choose to make a loan application:**
* It is scalable project: Nowadays, there are a lot of features that enhance the user experience, Therefore the app can always be changed and upgraded
* It is an excellent demo to practice real life appilcation offered by companies. 
* The process of building such application requires a lot of knowledge such as Threads and Design Patterns, SQL.
    
### Built With

* [JavaFX](https://openjfx.io/)
* [PostgreSQL](https://www.postgresql.org/)
* [Neural Network Architecture](https://github.com/liran121211/NeuralNetwork-From-Scratch-Java)
    
<!-- GETTING STARTED -->
## Getting Started

In order to run the app, please follow the instructions:

### Prerequisites

According to the application, the below plugins must be installed, whether through Maven.

JDK 18 - OpenJDK and above
[JDK 18.0](https://www.oracle.com/java/technologies/javase/jdk18-archive-downloads.html)
 
JavaFx
  
  ```
<!-- https://mvnrepository.com/artifact/org.openjfx/javafx -->
<dependency>
    <groupId>org.openjfx</groupId>
    <artifactId>javafx</artifactId>
    <version>11</version>
    <type>pom</type>
</dependency>
```
  
PostgreSQL
  
```
<!-- https://mvnrepository.com/artifact/org.postgresql/postgresql -->
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>42.3.5</version>
</dependency>
```
    
    
JUnit 4
  
```
<!-- https://mvnrepository.com/artifact/junit/junit -->
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.12</version>
    <scope>test</scope>
</dependency>
``` 
    
**All other dependencies can be viewed in the POM.xml file of the project**
    
### Installation

1. The application can be run through Java or jar file.
	
	[Java Version]("NOT AVAILABLE")
	
	[JAR Version]("NOT AVAILABLE")
    
## Screenshots

 **<u>**Welcome Window**</u>**:
	
![enter image description here](https://i.ibb.co/23Qc9ZV/welcome.png)
	  
	  
 **<u>**Login Window**</u>**:
	
![enter image description here](https://i.ibb.co/6mP7RXJ/login.png)
	  
**<u>**Client Window**</u>**:
	
![enter image description here](https://i.ibb.co/RTYFjfp/user-main.png)
	  
**<u>**Withdraw Window**</u>**:
	
![enter image description here](https://i.ibb.co/DWHKkPS/withdraw-request.png)
	  
**<u>**Reply Message Window**</u>**:
	
![enter image description here](https://i.ibb.co/kmyX4qk/view-message.png)
	  
**<u>**Todo List Window**</u>**:
	
![enter image description here](https://i.ibb.co/b1KBbtF/todo-list.png)
	  
**<u>**Reset Password Window**</u>**:
	
![enter image description here](https://i.ibb.co/d75c86w/reset-password.png)
	  
**<u>**Reigster Window**</u>**:
	
![enter image description here](https://i.ibb.co/vh0w1t5/register.png)
	  
**<u>**Modify Account Window**</u>**:
	
![enter image description here](https://i.ibb.co/s6TjyLh/modify-account.png)
	  
**<u>**Messages Window**</u>**:
	
![enter image description here](https://i.ibb.co/7JWNYHM/messages.png)

**<u>**Manage Users Window**</u>**:
	
![enter image description here](https://i.ibb.co/CmK0TGZ/manage-users.png)
	  
**<u>**Loans Panel Window**</u>**:
	
![enter image description here](https://i.ibb.co/s9RrgJR/loans-panel.png)
	  
**<u>**Loans Statistics Window**</u>**:
	
![enter image description here](https://i.ibb.co/fnKmWvz/loan-statistics.png)
	  
**<u>**Approved Loan Window**</u>**:
	
![enter image description here](https://i.ibb.co/rGBbN1r/loan-form8.png)
	  
**<u>**Rejected Loan Window**</u>**:
	
![enter image description here](https://i.ibb.co/xsV9sCW/loan-form7.png)
	  
**<u>**Loan Form Window**</u>**:
	
![enter image description here](https://i.ibb.co/K9tzYwy/loan-information.png)
	  
**<u>**Checking Loan Eligibility Window**</u>**:
	
![enter image description here](https://i.ibb.co/k0JDJsd/loan-form6.png)
	  
**<u>**Loan Agreement Window**</u>**:
	
![enter image description here](https://i.ibb.co/qM8K0SR/loan-form5.png)
	  
**<u>**Edit Profile Window**</u>**:
	
![enter image description here](https://i.ibb.co/X2Kkcjw/edit-profile.png)
	  
**<u>**Contact Banker Window**</u>**:
	
![enter image description here](https://i.ibb.co/NpXRT1b/contact-banker.png)
	  
**<u>**Complaint Form Window**</u>**:
	
![enter image description here](https://i.ibb.co/X4bnTSJ/complaint-form.png)
	  
**<u>**Banker Panel Window**</u>**:
	
![enter image description here](https://i.ibb.co/9cSBGvZ/banker-panel-1.png)
	  
**<u>**Banker Panel Window**</u>**:
	
![enter image description here](https://i.ibb.co/Tbhb58V/banker-panel-2.png)
	    	  
**<u>**About Window**</u>**:
	
![enter image description here](https://i.ibb.co/1n3Mrrd/about.png)
	  
**<u>**Loan Form #1 Window**</u>**:
	
![enter image description here](https://i.ibb.co/s5cL7bg/loan-form1.png)
	  
**<u>**Loan Form #2 Window**</u>**:
	
![enter image description here](https://i.ibb.co/LrCXSDP/loan-form2.png)
	  
**<u>**Loan Form #3 Window**</u>**:
	
![enter image description here](https://i.ibb.co/Jk0s5XP/loan-form3.png)
	  
**<u>**Loan Form #4 Window**</u>**:
	
![enter image description here](https://i.ibb.co/nsVGj3z/loan-form4.png)
	  
<!-- ROADMAP -->
## Features

 - Clients can receive positive or negative answer to their loan im minutes
 - User-Friendly Interface
 - Search users by keyword.
 - Take another loans by clients
 - Manage all loans in one pnael
 - Change your personal details
 - Logout to the Login Page.
 - Modify account details
 - Messages Panel
 - Fast registration process.
	  
<!-- CONTRIBUTING -->
## Limitations

To avoid problems when using the app, please note the following limitations:

1. Bigger data to load tables make changing screen much slower.
2. Any attempt to over flood the app with data insert will effect the performance of the application.
	  
<!-- LICENSE -->
## License

Distributed under the [GPLv3](https://www.gnu.org/licenses/gpl-3.0.html) License. 
	  
<!-- CONTACT -->
## Contact

<p>Tamar Aminov - [@LinkedIn](https://github.com/tamar1472) - tamaram@ac.sce.ac.il</p>
<p>Liran Smadja - [@LinkedIn](https://www.linkedin.com/in/liran-smadja/) - liransm@ac.sce.ac.il</p>
<p>Bathen Avraham - [@LinkedIn](https://github.com/bathen-avraham) - bathenav@ac.sce.ac.il</p>

<p>Other Projects: [See Now!](https://github.com/liran121211)</p>
