#include "sbdao.h"

SbDao::SbDao()
{
    db=dbcon.db;
    msg="";
    sbModel=new QSqlQueryModel;
}

bool SbDao::sbAdd(Shebei sb)
{
    QSqlQuery query;
    query.prepare("insert into shebei values (:sbid,:sbname,:sbtype,:sbchandi,:sbcount,:sbprovier,:sbprice,:sbdate,:sbtimeout)");
    query.bindValue(":sbid",sb.getSbid());
    query.bindValue(":sbname",sb.getSbname());
    query.bindValue(":sbtype",sb.getSbtype());
    query.bindValue(":sbchandi",sb.getSbchandi());
    query.bindValue(":sbcount",sb.getSbcount());
    query.bindValue(":sbprovier",sb.getSbprovider());
    query.bindValue(":sbprice",sb.getSbprice());
    query.bindValue(":sbdate",sb.getSbdate());
    query.bindValue(":sbtimeout",sb.getSbtimeout());
    isOk=query.exec();
    if(isOk)
    {
        //在query执行之后model再设置query
        sbModel->setQuery(query);
        return true;
    }
    else
    {
        msg=query.lastError().text();
        return false;
    }

}

bool SbDao::sbDelete(QString sbname)
{
    QSqlQuery query;
    query.prepare("delete from shebei where sbname=:sbname");
    query.bindValue(":sbname",sbname);
    isOk=query.exec();
    if(isOk)
    {
        sbModel->setQuery(query);
        return true;
    }
    else
    {
        msg=query.lastError().text();
        return false;
    }

}

bool SbDao::sbSelect(QString sbname)
{
    QSqlQuery query;
    query.prepare("select * from shebei where sbname=:sbname");
    query.bindValue(":sbname",sbname);
    isOk=query.exec();
    if(isOk)
    {
        sbModel->setQuery(query);
        sbModel->submit();
        return true;
    }
    else
    {
        msg=query.lastError().text();
        return false;
    }
}

bool SbDao::sbUpdate(Shebei sb)
{
    QSqlQuery query;
    qDebug()<<sb.getSbname();
    query.prepare("UPDATE shebei SET sbname = ?,sbtype = ?,sbchandi = ?,sbcount = ?,sbprovider = ?,sbprice = ?,sbdate = ?,sbtimeout = ? WHERE sbid = ?");
    query.bindValue(0,sb.getSbname());
    query.bindValue(1,sb.getSbtype());
    query.bindValue(2,sb.getSbchandi());
    query.bindValue(3,sb.getSbcount());
    query.bindValue(4,sb.getSbprovider());
    query.bindValue(5,sb.getSbprice());
    query.bindValue(6,sb.getSbdate());
    query.bindValue(7,sb.getSbtimeout());
    query.bindValue(8,sb.getSbid());

    isOk=query.exec();
    if(isOk)
    {
        sbModel->setQuery(query);
        return true;
    }
    else
    {
        msg=query.lastError().text();
        return false;
    }
}

bool SbDao::sbSelectAll()
{
    QSqlQuery query;
    query.prepare("select * from shebei");
    isOk=query.exec();
    if(isOk)
    {
        sbModel->setQuery(query);
        sbModel->submit();
        return true;
    }
    else
    {
        msg=query.lastError().text();
        return false;
    }
}
