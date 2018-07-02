#include "wxdao.h"

WxDao::WxDao()
{
    db=dbcon.db;
    msg="";
    wxModel=new QSqlQueryModel;
}


bool WxDao::wxAdd(Wx wx)
{
    QSqlQuery query;
    query.prepare("insert into weixiu values (:Wxid,:sbname,:yuanyin,:fuzeren,:wxdanwei,:wxdidian,:wxbeizhu,:wxdate,:wxshenheren)");
    query.bindValue(":Wxid",wx.Wxid);
    query.bindValue(":sbname",wx.sbname);
    query.bindValue(":yuanyin",wx.yuanyin);
    query.bindValue(":fuzeren",wx.fuzeren);
    query.bindValue(":wxdanwei",wx.wxdanwei);
    query.bindValue(":wxdidian",wx.wxdidian);
    query.bindValue(":wxbeizhu",wx.wxbeizhu);
    query.bindValue(":wxdate",wx.wxdate);
    query.bindValue(":wxshenheren",wx.wxshenheren);
    isOk=query.exec();
    if(isOk)
    {
        //在query执行之后model再设置query
        wxModel->setQuery(query);
        return true;
    }
    else
    {
        msg=query.lastError().text();
        return false;
    }

}

bool WxDao::wxDelete(QString sbname)
{
    QSqlQuery query;
    query.prepare("delete from weixiu where sbname=:sbname");
    query.bindValue(":sbname",sbname);
    isOk=query.exec();
    if(isOk)
    {
        wxModel->setQuery(query);
        return true;
    }
    else
    {
        msg=query.lastError().text();
        return false;
    }

}

bool WxDao::wxSelect(QString sbname)
{
    QSqlQuery query;
    query.prepare("select * from weixiu where sbname=:sbname");
    query.bindValue(":sbname",sbname);
    isOk=query.exec();
    if(isOk)
    {
        wxModel->setQuery(query);
        wxModel->submit();
        return true;
    }
    else
    {
        msg=query.lastError().text();
        return false;
    }
}

bool WxDao::wxUpdate(Wx wx)
{
    QSqlQuery query;
    qDebug()<<wx.sbname;
    query.prepare("UPDATE weixiu SET sbname = ?,yuanyin = ?,fuzeren = ?,wxdanwei = ?,wxdidian = ?,wxbeizhu = ?,wxdate = ?,wxshenheren = ? WHERE Wxid = ?");

    query.bindValue(0,wx.Wxid);
    query.bindValue(1,wx.yuanyin);
    query.bindValue(2,wx.fuzeren);
    query.bindValue(3,wx.wxdanwei);
    query.bindValue(4,wx.wxdidian);
    query.bindValue(5,wx.wxbeizhu);
    query.bindValue(6,wx.wxdate);
    query.bindValue(7,wx.wxshenheren);
    query.bindValue(8,wx.sbname);
    isOk=query.exec();
    if(isOk)
    {
        wxModel->setQuery(query);
        return true;
    }
    else
    {
        msg=query.lastError().text();
        return false;
    }
}

bool WxDao::wxSelectAll()
{
    QSqlQuery query;
    query.prepare("select * from weixiu");
    isOk=query.exec();
    if(isOk)
    {
        wxModel->setQuery(query);
        wxModel->submit();
        return true;
    }
    else
    {
        msg=query.lastError().text();
        return false;
    }
}
