Create Database FTMSProject;

Use FTMSProject;

CREATE TABLE Team(

	TeamId int IDENTITY(1,1) NOT NULL PRIMARY KEY,
	TeamName varchar(50) NOT NULL,
	MainCoach varchar(50) NOT NULL,
	AssistantCoach varchar(50) NOT NULL,
	Website varchar(50) NOT NULL,
	TotalPoint Numeric(5,2) NOT NULL check(TotalPoint>=0)
);

INSERT INTO Team 
VALUES ('ManCity', 'Johnson', 'Bernie', 'www.mancity.com', 0);
INSERT INTO Team 
VALUES ('ManUnited', 'Ron', 'Paul', 'www.manutd.com', 0);
INSERT INTO Team 
VALUES ('Chelsea', 'Peter', 'Stevens', 'www.chelsea.com', 0);
INSERT INTO Team 
VALUES ('Liverpool', 'Edward', 'Finch', 'www.liverpool.com', 0);
INSERT INTO Team 
VALUES ('Tottenham', 'Ben', 'Affleck', 'www.tottenham.com', 0);



CREATE TABLE Player(
	
	Player_ID int IDENTITY(1001,1) NOT NULL PRIMARY KEY,
	TeamId int foreign key references Team(TeamId),
	PName varchar(50) NOT NULL,
	Position varchar(50) NOT NULL,
	Injury_Status varchar(50) NOT NULL,
	Nationality varchar(50) NOT NULL,
	DOB date NOT NULL
);

INSERT INTO Player 
VALUES (2, 'Pogba', 'Midfielder', 'active', 'France','1989-7-11');
INSERT INTO Player 
VALUES (1, 'Aguero', 'Forward', 'active', 'Argentina','1991-3-23');
INSERT INTO Player 
VALUES (3, 'Hazard', 'Goalkeeper', 'injured', 'Belgium','1989-12-3');
INSERT INTO Player 
VALUES (4, 'Salah', 'Defender', 'active', 'Egypt','1990-1-15');
INSERT INTO Player 
VALUES (2, 'Rashford', 'Forward', 'injured', 'Germany','1992-9-21');
INSERT INTO Player 
VALUES (5, 'Kane', 'Forward', 'active', 'UK','1992-7-29');




select * from Team;
--Team Search
select TeamId,TeamName,MainCoach,AssistantCoach,Website from Team;
select * from Player;

CREATE TABLE Matches(
	
	Match_ID int IDENTITY(2001,1) NOT NULL PRIMARY KEY,
	Team1_Id int NOT NULL,
	Team2_Id int NOT NULL,
	Team1_Goal int NOT NULL,
	Team2_Goal int NOT NULL,
	Referee1_Name varchar(50) NOT NULL,
	Referee2_Name varchar(50) NOT NULL,
	Duration varchar(50) NOT NULL,
	Stadium varchar(50) NOT NULL,
	Date_of_Match date NOT NULL,
	Man_of_the_Match varchar(50) NOT NULL,
	Winner_Team_Id int NOT NULL
);

INSERT INTO Matches 
VALUES (1, 3, 2, 4, 'Smith', 'Arthur', '90:00','London','2007-11-28','Aguero',1);
INSERT INTO Matches 
VALUES (2, 3, 2, 3, 'Roy', 'Douglas', '108:00','Munich','2017-10-07','Hazard',3);
INSERT INTO Matches 
VALUES (4, 1, 0, 2, 'John', 'Morrison', '90:00','Paris','2013-9-17','Aguero',1);
INSERT INTO Matches 
VALUES (4, 5, 1, 0, 'Altaf', 'Carter', '90:00','Rome','2018-3-1','Salah',4);
INSERT INTO Matches 
VALUES (2, 3, 2, 1, 'Chris', 'Carter', '90:00','Rome','2018-3-1','Pogba',2);
INSERT INTO Matches 
VALUES (2, 5, 2, 1, 'Morris', 'Carter', '95:00','Munich','2018-3-1','Kane',5);

--constraint
ALTER table Matches ADD CONSTRAINT
cgoal1 check(Team1_Goal>=0);
ALTER table Matches ADD CONSTRAINT
cgoal2 check(Team2_Goal>=0);

ALTER table Matches Drop CONSTRAINT
cgoal1;
ALTER table Matches Drop CONSTRAINT
cgoal2;

select * from Matches;

--point table
--select * from Team inner join Matches on Team.TeamId= Matches.Winner_Team_Id  ;
--select Team.TeamId,Team.TeamName,count(Matches.Winner_Team_Id) as 'Matches_Won' into WinStats from Team inner join Matches on Team.TeamId=Matches.Winner_Team_Id group by Team.TeamId,Team.TeamName;
--select Team.TeamId,Team.TeamName,count(Match_ID) as 'Matches_Played' into PlayStats from Team inner join Matches on Team.TeamId=Matches.Team2_Id or Team.TeamId=Matches.Team1_Id group by Team.TeamId,Team.TeamName;
--select PlayStats.TeamId,PlayStats.TeamName,PlayStats.Matches_Played,WinStats.Matches_Won,(PlayStats.Matches_Played-WinStats.Matches_Won) as 'Matches_Lost',(WinStats.Matches_Won*2.5) as 'Total_Points' from WinStats full join PlayStats on WinStats.TeamId=PlayStats.TeamId order by (WinStats.Matches_Won*2.5) desc;

--point table final
select TeamId,TeamName,count(Match_ID) as 'Matches_Played',(select count(Winner_Team_Id) from Matches where Team.TeamId=Matches.Winner_Team_Id) as 'Matches_Won',(count(Match_ID)-(select count(Winner_Team_Id) from Matches where Team.TeamId=Matches.Winner_Team_Id)) as 'Matches_Lost',((select count(Winner_Team_Id) from Matches where Team.TeamId=Matches.Winner_Team_Id)*3.0) as 'Total_Points' from Team inner join Matches on Team.TeamId=Matches.Team2_Id or Team.TeamId=Matches.Team1_Id group by Team.TeamId,Team.TeamName order by ((select count(Winner_Team_Id) from Matches where Team.TeamId=Matches.Winner_Team_Id)*3.0) desc;

CREATE TABLE Goal(
	
	Goal_ID int IDENTITY(3001,1) NOT NULL PRIMARY KEY,
	Player_ID Int NOT NULL foreign key references Player(Player_ID),
	Match_ID Int NOT NULL foreign key references Matches(Match_ID),
	GTime varchar(15) NOT NULL
);


INSERT INTO Goal 
VALUES (1002, 2001,'10:00');
INSERT INTO Goal 
VALUES (1003, 2002,'14:00');
INSERT INTO Goal 
VALUES (1002, 2003,'56:00');
INSERT INTO Goal 
VALUES (1004, 2004,'17:00');
INSERT INTO Goal 
VALUES (1003, 2002,'28:00');
INSERT INTO Goal 
VALUES (1001, 2005,'48:00');
INSERT INTO Goal 
VALUES (1005, 2006,'22:00');
INSERT INTO Goal 
VALUES (1005, 2006,'67:00');
INSERT INTO Goal 
VALUES (1003, 2002,'90:00');

--update
UPDATE Goal SET GTime='07:00' where Player_ID=1002 and Match_ID=2001;

select * from Goal;
--select Player_ID,Match_ID,GTime from Goal order by Match_ID;
--select Goal.Player_ID as 'Scorer_ID',(Select PName from Player where Player.Player_ID=Goal.Player_ID) as 'Scorer',(Select TeamName from Team where Team.TeamId=Player.TeamId) as 'Team',(select Team1_Id as 'Team1ID' from Matches where Match_ID IN (select Match_ID from Goal)),(select Team2_Id as 'Team2ID' from Matches where Match_ID IN (select Match_ID from Goal)),GTime from Goal,Player order by Match_ID;
--select Match_ID,Goal.Player_ID as 'Scorer_ID',(Select PName from Player where Player.Player_ID=Goal.Player_ID) as 'Scorer',(Select TeamName from Team where Team.TeamId=Player.TeamId) as 'Team',(select (select TeamName as 'Team1_Name' from Team where Team.TeamId=Matches.Team1_Id ) from Matches where Match_ID IN (select Match_ID from Goal)),(select (select TeamName as 'Team2_Name' from Team where Team.TeamId=Matches.Team2_Id ) from Matches where Match_ID IN (select Match_ID from Goal)),GTime from Goal,Player order by Match_ID;

--Goals(Inner join)
--select Match_ID,Goal.Player_ID as 'Scorer_ID',(Select PName from Player where Player.Player_ID=Goal.Player_ID) as 'Scorer',(Select TeamName from Team where Team.TeamId=Player.TeamId) as 'Team',(select (select TeamName as 'Team1_Name' from Team where Team.TeamId=Matches.Team1_Id ) from Matches where Match_ID IN (select Match_ID from Goal)),(select (select TeamName as 'Team2_Name' from Team where Team.TeamId=Matches.Team2_Id ) from Matches where Match_ID IN (select Match_ID from Goal)),GTime from Goal inner join Player on Goal.Player_ID=Player.Player_ID order by Match_ID;
--select Match_ID,Goal.Player_ID as 'Scorer_ID',(Select PName from Player where Player.Player_ID=Goal.Player_ID) as 'Scorer',(Select TeamName from Team where Team.TeamId=Player.TeamId) as 'Team',(select (select TeamName as 'Team1_Name' from Team where Team.TeamId=Matches.Team1_Id ) from Matches where Match_ID =any (select Match_ID from Goal)),(select (select TeamName as 'Team2_Name' from Team where Team.TeamId=Matches.Team2_Id ) from Matches where Match_ID =any (select Match_ID from Goal)),GTime from Goal inner join Player on Goal.Player_ID=Player.Player_ID order by Match_ID;
--select Match_ID,Goal.Player_ID as 'Scorer_ID',(Select PName from Player where Player.Player_ID=Goal.Player_ID) as 'Scorer',(Select TeamName from Team where Team.TeamId=Player.TeamId) as 'Team',GTime from Goal inner join Player on Goal.Player_ID=Player.Player_ID order by Match_ID;

select Goal.Match_ID,(Select TeamName from Team where Team.TeamId=Matches.Team1_Id) as 'Team1Name',(Select TeamName from Team where Team.TeamId=Matches.Team2_Id) as 'Team2Name',Goal.Player_ID as 'Player_ID',Player.PName as 'Player_Name',Team.TeamName  as 'Team',Goal.GTime from Goal inner join Player on Goal.Player_ID=Player.Player_ID inner join Team on Team.TeamId=Player.TeamId inner join Matches on Matches.Match_ID=Goal.Match_ID order by Goal.Match_ID;

--Matches
select Date_of_Match,(select TeamName from Team where Team.TeamId=Matches.Team1_Id ) as 'Team1',Team1_Goal,(select TeamName from Team where Team.TeamId=Matches.Team2_Id ) as 'Team2',Team2_Goal,Duration,Man_of_the_Match from Matches order by Date_of_Match;

--select Date_of_Match,(select TeamName from Team where Team.TeamId=Matches.Team1_Id ) as 'Team1',Team1_Goal,(select TeamName from Team where Team.TeamId=Matches.Team2_Id ) as 'Team2',Team2_Goal,(select Player_ID from Goal where Goal.Match_ID=Matches.Match_ID) as 'Goals',Duration,Man_of_the_Match from Matches order by Date_of_Match;

--player details
select Player_ID,PName,TeamName,Position,Injury_Status,Nationality,DOB from Player inner join Team on Player.TeamId=Team.TeamId;

--stats(Goals)
--select Player_ID,(Select PName as 'Name' from Player where Player.Player_ID = Goal.Player_ID),(Select TeamName as 'Team' from Team where Team.TeamId IN (Select Player.TeamId from Player)),count(Goal_ID) as 'Total_Goals' from Goal group by Player_ID order by count(Goal_ID) desc;
--select Player_ID,(Select PName as 'Name' from Player where Player.Player_ID = Goal.Player_ID),count(Goal_ID) as 'Total_Goals' from Goal group by Player_ID order by count(Goal_ID) desc;

select Goal.Player_ID,Player.PName,(Select TeamName from Team where Team.TeamId=Player.TeamId) as 'Team',count(Goal_ID) as 'Total_Goals' from Goal inner join Player on Goal.Player_ID=Player.Player_ID group by Goal.Player_ID,Player.TeamId,Player.PName order by count(Goal_ID) desc;


CREATE TABLE Cards(
	
	Card_ID int IDENTITY(4001,1) NOT NULL PRIMARY KEY,	
	Player_ID Int NOT NULL foreign key references Player(Player_ID),
	Match_ID Int NOT NULL foreign key references Matches(Match_ID),
	Card_Type varchar(15) NOT NULL,
);

select * from Cards;

INSERT INTO Cards 
VALUES (1001, 2005,'Yellow');
INSERT INTO Cards 
VALUES (1001, 2005,'Yellow');
INSERT INTO Cards 
VALUES (1002, 2003,'Yellow');
INSERT INTO Cards 
VALUES (1003, 2002,'Red');
INSERT INTO Cards 
VALUES (1004, 2004,'Red');

--Cards(Inner join)
--select Match_ID,Cards.Player_ID as 'Player_ID',(Select PName from Player where Player.Player_ID=Cards.Player_ID) as 'Player_Name',(Select TeamName from Team where Team.TeamId=Player.TeamId) as 'Team',Card_Type from Cards inner join Player on Cards.Player_ID=Player.Player_ID order by Match_ID;

--select Cards.Match_ID,Matches.Team1_Id,(Select TeamName as 'Team1' from Team where Team.TeamId=Matches.Team1_Id),Matches.Team2_Id,(Select TeamName as 'Team1' from Team where Team.TeamId=Matches.Team2_Id),Cards.Player_ID as 'Player_ID',(Select PName from Player where Player.Player_ID=Cards.Player_ID) as 'Player_Name',(Select TeamName from Team where Team.TeamId=Player.TeamId) as 'Team',Card_Type from Cards inner join Player on Cards.Player_ID=Player.Player_ID inner join Team on Team.TeamId=Player.TeamId inner join Matches on Matches.Match_ID=Cards.Match_ID order by Cards.Match_ID;
--select Cards.Match_ID,(Select TeamName as 'Team1' from Team where Team.TeamId=Matches.Team1_Id),(Select TeamName as 'Team2' from Team where Team.TeamId=Matches.Team2_Id),Cards.Player_ID as 'Player_ID',(Select PName from Player where Player.Player_ID=Cards.Player_ID) as 'Player_Name',(Select TeamName from Team where Team.TeamId=Player.TeamId) as 'Team',Card_Type from Cards inner join Player on Cards.Player_ID=Player.Player_ID inner join Team on Team.TeamId=Player.TeamId inner join Matches on Matches.Match_ID=Cards.Match_ID order by Cards.Match_ID;

select Cards.Match_ID,(Select TeamName from Team where Team.TeamId=Matches.Team1_Id) as 'Team1Name',(Select TeamName from Team where Team.TeamId=Matches.Team2_Id) as 'Team2Name',Cards.Player_ID as 'Player_ID',Player.PName as 'Player_Name',Team.TeamName  as 'Team',Card_Type from Cards inner join Player on Cards.Player_ID=Player.Player_ID inner join Team on Team.TeamId=Player.TeamId inner join Matches on Matches.Match_ID=Cards.Match_ID order by Cards.Match_ID;

--stats(Yellow Cards)
--select Cards.Player_ID,Player.PName,(Select PName as 'Name' from Player where Player.Player_ID = Cards.Player_ID),(Select TeamName from Team where Team.TeamId=Player.TeamId),count(Card_ID) as 'Total_Cards' from Cards inner join Player on Cards.Player_ID=Player.Player_ID where Card_Type='Yellow' group by Cards.Player_ID,Player.TeamId,Player.PName order by count(Card_ID) desc;

select Cards.Player_ID,Player.PName,(Select TeamName from Team where Team.TeamId=Player.TeamId) as 'Team',count(Card_ID) as 'Total_Cards' from Cards inner join Player on Cards.Player_ID=Player.Player_ID where Card_Type='Yellow' group by Cards.Player_ID,Player.TeamId,Player.PName order by count(Card_ID) desc;

--stats(Red Cards)
--select Player_ID,(Select PName as 'Name' from Player where Player.Player_ID = Cards.Player_ID),count(Card_ID) as 'Total_Cards' from Cards where Card_Type='Red' group by Player_ID order by count(Card_ID) desc;

select Cards.Player_ID,Player.PName,(Select TeamName from Team where Team.TeamId=Player.TeamId) as 'Team',count(Card_ID) as 'Total_Cards' from Cards inner join Player on Cards.Player_ID=Player.Player_ID where Card_Type='Red' group by Cards.Player_ID,Player.TeamId,Player.PName order by count(Card_ID) desc;

--highest goals in tournament
--select top 1 Goal.Player_ID,Player.PName,count(Goal_ID) from Goal join Player on Goal.Player_ID=Player.Player_ID group by Goal.Player_ID,Player.PName;
select top 1 Player.PName,count(Goal_ID) as 'Total Goals' from Goal inner join Player on Goal.Player_ID=Player.Player_ID group by Goal.Player_ID,Player.PName order by count(Goal_ID) desc;


drop table Matches;
--drop table WinStats;
--drop table PlayStats;
drop table Player;
drop table Team;
drop table Goal;
drop table Cards;