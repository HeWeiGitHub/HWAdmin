#include "dbcon.h"

DBCon::DBCon()
{
    db=QSqlDatabase::addDatabase("QMYSQL");
    conmsg="QMYSQL"+db.isValid();
    qDebug()<<conmsg;
    QStringList driverlist=QSqlDatabase::drivers();

    foreach (QString var, driverlist) {
        qDebug()<<var;
    }

    db.setHostName("localhost");
    db.setDatabaseName("hw");
    db.setUserName("root");
    db.setPassword("1234");
    bool b=db.open();
    if(b)
    {
        conmsg=conmsg+"  database is open";
        qDebug()<<conmsg;
    }
    else
    {
        conmsg=conmsg+"  database is open failed";
        qDebug()<<conmsg;
    }

}

