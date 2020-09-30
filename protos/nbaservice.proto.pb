
—
nbaservice.proto"#
	LoginUser
userId (	RuserId"<
Page
pageNum (RpageNum
pageSize (RpageSize"Ø
Player
playerId (RplayerId
	firstName (	R	firstName
lastName (	RlastName
team (	Rteam
teamId (RteamId
jersey (Rjersey
position (	Rposition
height (Rheight
weight	 (Rweight
	birthDate
 (	R	birthDate
	birthCity (	R	birthCity

birthState (	R
birthState
college (	Rcollege
salary (Rsalary
photoUrl (	RphotoUrl

experience (R
experience

playerName (	R
playerName"ï
GetNbaPlayerListReq
pageNum (RpageNum
pageSize (RpageSize

playerName (	R
playerName
position (	Rposition
playerId (RplayerId
team (	Rteam
teamId (RteamId
userd (2
.LoginUserRuser"€
GetNbaPlayerListRes!
players (2.PlayerRplayers
total (Rtotal
retCode (RretCode
retMsg (	RretMsg"P
GetPlayerDetailReq
playerId (RplayerId
userd (2
.LoginUserRuser"g
GetPlayerDetailRes
player (2.PlayerRplayer
retCode (RretCode
retMsg (	RretMsg"ª
Team
teamName (	RteamName
city (	Rcity

conference (	R
conference
division (	Rdivision
	stadiumId (R	stadiumId
logoUrl (	RlogoUrl"J
GetTeamDetailReq
teamId (RteamId
userd (2
.LoginUserRuser"_
GetTeamDetailRes
team (2.TeamRteam
retCode (RretCode
retMsg (	RretMsgB3
com.rivers.nbaservice.protoBNbaServiceMetasPˆbproto3