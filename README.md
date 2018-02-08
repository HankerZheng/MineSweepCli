# MineSweepCli
This is just a practice of how we should design the structure of a simple java project.

This project is not about implementing the game itself, but about how to group different components and make the project extensible.

I would iterate on this project for several times to refine the strucutre of the project. And each history version of the porject can be found in different branches. The master branch would be at the newest version of the project.

The change and structure details would be mentioned in the `DevThought.md` file.


## Versions
- v0.1.20180202
- v0.2.20180202 <- current version
- v0.3.20180202 <- master


# Dependency
- `mvn`

# Installation
- Download this git repository with `git clone https://github.com/HankerZheng/MineSweepCli.git`
- Open the project directory `cd MineSweepCli`
- Build the project `mvn clean package`
- Change the branch to current version `git checkout v0.2.20180202`
- Run the application `java -jar ./target/minesweep.cli-1.0-SNAPSHOT.jar`

