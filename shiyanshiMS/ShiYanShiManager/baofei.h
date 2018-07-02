#ifndef BAOFEI_H
#define BAOFEI_H

#include "bf.h"
#include <QCloseEvent>
#include <QDialog>

namespace Ui {
class BaoFei;
}

class BaoFei : public QDialog
{
    Q_OBJECT

public:
    explicit BaoFei(QWidget *parent = 0);
    ~BaoFei();

private:
    Ui::BaoFei *ui;

public slots:
    void pressUp();
    void pressIn();
    void closeEvent(QCloseEvent *event);
signals:
    void upBf(Bf bf);
    void inBf(Bf bf);
};

#endif // BAOFEI_H
