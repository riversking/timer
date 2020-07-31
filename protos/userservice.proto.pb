
Ø
userservice.proto"#
	LoginUser
userId (	RuserId"<
Page
pageNum (RpageNum
pageSize (RpageSize"(

AddUserReq
username (	Rusername"+
UpdateUserReq
username (	Rusername"1
GetMenuByUIdReq
user (2
.LoginUserRuser"õ
Menu
id (Rid
checked (Rchecked!
children (2.MenuRchildren
	component (	R	component
expand (Rexpand
icon (	Ricon
label (	Rlabel
name (	Rname
parentId	 (RparentId
path
 (	Rpath
show (Rshow
sort (Rsort
spread (Rspread
title (	Rtitle
type (Rtype
code (	Rcode"^
GetMenuByUIdRes
menu (2.MenuRmenu
retCode (RretCode
retMsg (	RretMsg" 
User
username (	Rusername
nickname (	Rnickname
mail (	Rmail
phone (	Rphone
userId (	RuserId

createDate (	R
createDate"·
GetUserListReq
username (	Rusername
mobile (	Rmobile
	startDate (	R	startDate
endDate (	RendDate
user (2
.LoginUserRuser
page (2.PageRpage"u
GetUserListRes
users (2.UserRusers
retCode (RretCode
retMsg (	RretMsg
total (Rtotal"—
UpdateRoleByIdReq
id (Rid
roleName (	RroleName
roleCode (	RroleCode
roleDesc (	RroleDesc
userd (2
.LoginUserRuser"E
UpdateRoleByIdRes
retCode (RretCode
retMsg (	RretMsg"C
DeleteDeptByIdReq
id (Rid
userd (2
.LoginUserRuser"E
DeleteDeptByIdRes
retCode (RretCode
retMsg (	RretMsgB5
com.rivers.userservice.protoBUserServiceMetasPˆbproto3