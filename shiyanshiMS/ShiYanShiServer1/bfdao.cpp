#include "bfdao.h"

BfDao::BfDao()
{
    db=dbcon.db;
    msg="";
    bfModel=new QSqlQueryModel;
}

bool BfDao::bfAdd(Bf bf)
{
    QSqlQuery query;
    query.prepare("insert into baofei values (:bfid,:sbname,:sbtype,:fuzeren,:bfbeizhu,:bfdate,:cunfangdidian,:bfshenheren)");
    query.bindValue(":bfid",bf.bfid);
    query.bindValue(":sbname",bf.sbname);
    query.bindValue(":sbtype",bf.sbtype);
    query.bindValue(":fuzeren",bf.fuzeren);
    query.bindValue(":bfbeizhu",bf.bfbeizhu);
    query.bindValue(":bfdate",bf.bfdate);
    query.bindValue(":cunfangdidian",bf.cunfangdidian);
    query.bindValue(":bfshenheren",bf.bfshenheren);
    isOk=query.exec();
    if(isOk)
    {
        //在query执行之后model再设置query
        bfModel->setQuery(query);
        return true;
    }
    else
    {
        msg=query.lastError().text();
        return false;
    }

}

bool BfDao::bfDelete(QString sbname)
{
    QSqlQuery query;
    query.prepare("delete from baofei where sbname=:sbname");
    query.bindValue(":sbname",sbname);
    isOk=query.exec();
    if(isOk)
    {
        bfModel->setQuery(query);
        return true;
    }
    else
    {
        msg=query.lastError().text();
        return false;
    }

}

bool BfDao::bfSelect(QString sbname)
{
    QSqlQuery query;
    query.prepare("select * from baofei where sbname=:sbname");
    query.bindValue(":sbname",sbname);
    isOk=query.exec();
    if(isOk)
    {
        bfModel->setQuery(query);
        bfModel->submit();
        return true;
    }
    else
    {
        msg=query.lastError().text();
        return false;
    }
}

bool BfDao::bfUpdate(Bf bf)
{
    QSqlQuery query;
    qDebug()<<bf.sbname;
    query.prepare("UPDATE baofei SET sbname = ?,sbtype = ?,fuzeren = ?,bfbeizhu = ?,bfdate = ?,cunfangdidian = ?,bfshenheren = ? WHERE bfid= ?");
    query.bindValue(0,bf.sbname);
    query.bindValue(1,bf.sbtype);
    query.bindValue(2,bf.fuzeren);
    query.bindValue(3,bf.bfbeizhu);
    query.bindValue(4,bf.bfdate);
    query.bindValue(5,bf.cunfangdidian);
    query.bindValue(6,bf.bfshenheren);
    query.bindValue(7,bf.bfid);

    isOk=query.exec();
    if(isOk)
    {
        bfModel->setQuery(query);
        return true;
    }
    else
    {
        msg=query.lastError().text();
        return false;
    }
}

bool BfDao::bfSelectAll()
{
    QSqlQuery query;
    query.prepare("select * from baofei");
    isOk=query.exec();
    if(isOk)
    {
        bfModel->setQuery(query);
        bfModel->submit();
        return true;
    }
    else
    {
        msg=query.lastError().text();
        return false;
    }
}
