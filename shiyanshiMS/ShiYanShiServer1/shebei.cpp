#include "shebei.h"

Shebei::Shebei()
{

}

qint8 Shebei::getSbid()
{
    return this->sbid;
}
void Shebei::setSbId(qint8 id)
{
    this->sbid=id;
}

QString Shebei::getSbchandi()
{
    return this->sbchandi;
}
void Shebei::setSbchandi(QString chandi)
{
    this->sbchandi=chandi;
}

QString Shebei::getSbname()
{
    return this->sbname;
}
void Shebei::setSbname(QString sbname)
{
    this->sbname=sbname;
}

qint8 Shebei::getSbcount()
{
    return this->sbcount;
}

void Shebei::setSbcount(qint8 count)
{
    this->sbcount=count;
}

QString Shebei::getSbprice()
{
    return this->sbprice;
}
void Shebei::setSbprice(QString price)
{
    this->sbprice=price;
}

QString Shebei::getSbprovider()
{
    return this->sbprovider;
}
void Shebei::setSbprovider(QString provider)
{
    this->sbprovider=provider;
}

QString Shebei::getSbtype()
{
    return this->sbtype;
}
void Shebei::setSbtype(QString date)
{
    this->sbtype=date;
}

QString Shebei::getSbdate()
{
    return this->sbdate;
}
void Shebei::setSbdate(QString date)
{
    this->sbdate=date;
}

QString Shebei::getSbtimeout()
{
    return this->sbtimeout;
}
void Shebei::setSbtimeout(QString timeout)
{
    this->sbdate=timeout;
}
