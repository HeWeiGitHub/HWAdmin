#ifndef WEIXIU_H
#define WEIXIU_H

#include <QDialog>
#include <QCloseEvent>
#include "wx.h"

namespace Ui {
class WeiXiu;
}

class WeiXiu : public QDialog
{
    Q_OBJECT

public:
    explicit WeiXiu(QWidget *parent = 0);
    ~WeiXiu();

private:
    Ui::WeiXiu *ui;

public slots:
    void pressUp();
    void pressIn();
    void closeEvent(QCloseEvent *event);
signals:
    void upWx(Wx wx);
    void inWx(Wx wx);
};

#endif // WEIXIU_H
