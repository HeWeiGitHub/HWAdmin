#ifndef SHEBEI_H
#define SHEBEI_H
#include <QString>

class Shebei
{
public:
    Shebei();
public:
    void setSbname(QString name);
    void setSbprovider(QString provider);
    void setSbchandi(QString chandi);
    void setSbId(qint8 id);
    void setSbtype(QString type);
    void setSbcount(qint8 count);
    void setSbprice(QString price);
    void setSbdate(QString date);
    void setSbtimeout(QString timeout);

    qint8 getSbid();
    QString getSbname();
    QString getSbprovider();
    QString getSbtype();
    QString getSbchandi();
    qint8 getSbcount();
    QString getSbprice();
    QString getSbdate();
    QString getSbtimeout();
private:
    int sbid;
    QString sbname;
    QString sbtype;
    QString sbchandi;
    int sbcount;
    QString sbprovider;
    QString sbprice;
    QString sbdate;
    QString sbtimeout;
};

#endif // SHEBEI_H
