
* userdao

total
===
select count(*) from user

getUsersByName
===
select * from user where name like #'%'+name+'%'#

allUsers
===
select * from user

updateUser
===
update user set name=#name# where id=#id#

getUserById
===
select * from user u where 1=1
 @if(!isEmpty(id)){
     and u.id=#id#
 @}


