# **Text Processing and Data Management Tool Documentation**

## **Project Overview**
The Text Processing and Data Management Tool is designed to facilitate various text manipulation operations, including search, replace, and data management using Java collections. The project emphasizes collaboration within a Scrum team, adhering to Git and GitHub workflows, and maintaining high standards of code quality and documentation.

## **Technologies Used**
- Java JDK 21
- Maven
- JavaFX

## **Project Objectives**
- Implement Scrum processes within the project workflow.
- Integrate Git and GitHub workflows using Gitflow branching strategy.
- Facilitate effective collaboration and communication among team members.
- Enforce guidelines for writing descriptive commit messages.
- Evaluate engineers' adherence to Scrum processes, GitHub workflows, and collaborative practices.
- Use regular expressions (regex) to search, match, and replace text patterns.
- Understand the fundamentals of Java collections (ArrayList, Set, Map) and their usages.
- Create user interfaces using JavaFX for interaction with the text processing tool.
- Testing

## **Implementation**
- **Scrum Implementation**
  - Sprint Planning: Follow sprint goals and allocate tasks.
  - Daily Standups: Conduct standup meetings every 2 hours of work.
  - Sprint Reviews and Retrospectives: Evaluate progress and identify improvement areas.
- **Git and GitHub Usage**
  - Gitflow Branching Strategy: Manage feature development.
  - Git Commands: Use branching, merging, rebasing, and resolving conflicts.
  - Pull Requests and Code Reviews: Create pull requests, review code, and provide feedback.
- **Collaboration Practices**
  - Communication Channels: Utilize Slack for team communication and Jira for project management and progress tracking.
- **Proper Commit Messages**
  - Commit Messages: Follow guidelines for writing clear and informative commit messages.

## **Features**
- **Regular Expressions (Regex) Module**
  - Functionality: Search, match, and replace text using regular expressions.
  - Interface: User-friendly interface for inputting regex patterns and text data.
- **Text Processing Module**
  - Methods: Search for patterns and replace characters in text data using regex.
  - Classes: Utilize Java's Pattern and Matcher classes for regex operations.
  - Display: Show match results and replaced text to the user.
- **Data Management Module**
  - Collections: Utilize Java collections (ArrayList, Set, Map) for managing data.
  - Operations: Operate entries in collections.
  - Custom Objects: Implement hashCode and different methods for proper collection operations.
- **User Interface**
  - Framework: Develop a user interface using JavaFX.
  - Interaction: Options for inputting text data, regex patterns, and performing operations.
  - Results: Display results of regex operations and collection manipulations.

## **Getting Started**
1. Clone the repository: `git clone "https://github.com/NadegeGaju/Text-Processing-and-Data-management-Tool.git"`
2. Navigate to the project directory: `cd text-processing-tool`
3. Build the project: `mvn clean install`
4. Run the application: `java -jar target/text-processing-tool.jar`
