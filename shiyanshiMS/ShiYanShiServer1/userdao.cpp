#include "userdao.h"

USERDAO::USERDAO()
{
    db=dbcon.db;
    msg="";
    uModel=new QSqlQueryModel;
}

bool USERDAO::uAdd(User user)
{
    QSqlQuery query;
    query.prepare("insert into users values (:uid,:uname,:upass,:uphone,:utype)");
    query.bindValue(":uid",user.getUid());
    query.bindValue(":uname",user.getUname());
    query.bindValue(":upass",user.getUpass());
    query.bindValue(":uphone",user.getUphone());
    query.bindValue(":utype",user.getUtype());
    isOk=query.exec();
    if(isOk)
    {
        //在query执行之后model再设置query
        uModel->setQuery(query);
        return true;
    }
    else
    {
        msg=query.lastError().text();
        return false;
    }
}

bool USERDAO::uDelete(QString uname)
{
    QSqlQuery query;
    query.prepare("delete from users where uname=:uname");
    query.bindValue(":uname",uname);
    isOk=query.exec();
    if(isOk)
    {
        uModel->setQuery(query);
        return true;
    }
    else
    {
        msg=query.lastError().text();
        return false;
    }
}

bool USERDAO::uSelect(QString uname)
{
    QSqlQuery query;
    query.prepare("select * from users where uname=:uname");
    query.bindValue(":uname",uname);
    isOk=query.exec();
    if(isOk)
    {
        uModel->setQuery(query);
        return true;
    }
    else
    {
        msg=query.lastError().text();
        return false;
    }
}

bool USERDAO::uUpdate(User user)
{
    QSqlQuery query;
    query.prepare("UPDATE users SET uname = ?,upass=?,uphone=?,sbtype = ? WHERE uid = ?");
    query.bindValue(0,user.getUname());
    query.bindValue(1,user.getUpass());
    query.bindValue(2,user.getUphone());
    query.bindValue(3,user.getUtype());
    query.bindValue(4,user.getUid());
    isOk=query.exec();
    if(isOk)
    {
        uModel->setQuery(query);
        return true;
    }
    else
    {
        msg=query.lastError().text();
        return false;
    }
}

bool USERDAO::uSelectAll()
{
    QSqlQuery query;
    query.prepare("select * from users");
    isOk=query.exec();
    if(isOk)
    {
        uModel->setQuery(query);
        return true;
    }
    else
    {
        msg=query.lastError().text();
        return false;
    }
}
