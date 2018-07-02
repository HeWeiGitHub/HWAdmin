#-------------------------------------------------
#
# Project created by QtCreator 2016-03-23T10:09:55
#
#-------------------------------------------------

QT       += core gui
QT       += sql
QT       += network
greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

TARGET = ShiYanShiManager
TEMPLATE = app


SOURCES += main.cpp\
        mainwindow.cpp \
    inupdialog.cpp \
    sededialog.cpp \
    login.cpp \
    shebei.cpp \
    user.cpp \
    servlogin.cpp \
    weixiu.cpp \
    baofei.cpp \
    wx.cpp \
    bf.cpp \
    aboutdialog.cpp \
    userdlg.cpp

HEADERS  += mainwindow.h \
    inupdialog.h \
    sededialog.h \
    login.h \
    shebei.h \
    user.h \
    servlogin.h \
    weixiu.h \
    wx.h \
    bf.h \
    baofei.h \
    aboutdialog.h \
    userdlg.h

FORMS    += mainwindow.ui \
    inupdialog.ui \
    sededialog.ui \
    login.ui \
    servlogin.ui \
    weixiu.ui \
    baofei.ui \
    aboutdialog.ui \
    userdlg.ui

DISTFILES += \
    ico/user.ai \
    ico/man.bmp \
    ico/user.bmp \
    ico/admin.ico \
    ico/ARW06DN.ICO \
    ico/ARW06LT.ICO \
    ico/ARW06RT.ICO \
    ico/ARW06UP.ICO \
    ico/ARW08LT.ICO \
    ico/ARW08RT.ICO \
    ico/book.ico \
    ico/btw.ico \
    ico/cd-rom.ico \
    ico/db.ico \
    ico/disk.ico \
    ico/down1.ICO \
    ico/EAR.ICO \
    ico/explorer.ico \
    ico/EYE.ICO \
    ico/FACE02.ICO \
    ico/FACE04.ICO \
    ico/favicon.ico \
    ico/file.ico \
    ico/FLGASTRL.ICO \
    ico/FLGAUSTA.ICO \
    ico/FLGBELG.ICO \
    ico/FLGBRAZL.ICO \
    ico/FLGCAN.ICO \
    ico/FLGDEN.ICO \
    ico/FLGFIN.ICO \
    ico/FLGFRAN.ICO \
    ico/FLGGERM.ICO \
    ico/FLGIREL.ICO \
    ico/FLGITALY.ICO \
    ico/FLGJAPAN.ICO \
    ico/FLGMEX.ICO \
    ico/FLGNETH.ICO \
    ico/FLGNORW.ICO \
    ico/FLGNZ.ICO \
    ico/FLGPORT.ICO \
    ico/FLGRSA.ICO \
    ico/FLGRUS.ICO \
    ico/FLGSKOR.ICO \
    ico/FLGSPAIN.ICO \
    ico/FLGSWED.ICO \
    ico/FLGSWITZ.ICO \
    ico/FLGTURK.ICO \
    ico/FLGUK.ICO \
    ico/FLGUSA01.ICO \
    ico/FLGUSA02.ICO \
    ico/folder open.ico \
    ico/folder.ico \
    ico/folder2.ico \
    ico/GRAPH01.ICO \
    ico/GRAPH02.ICO \
    ico/GRAPH03.ICO \
    ico/GRAPH04.ICO \
    ico/GRAPH05.ICO \
    ico/GRAPH07.ICO \
    ico/GRAPH08.ICO \
    ico/GRAPH09.ICO \
    ico/GRAPH11.ICO \
    ico/GRAPH12.ICO \
    ico/GRAPH15.ICO \
    ico/group.ico \
    ico/hand.ico \
    ico/Help.ico \
    ico/help1.ico \
    ico/hourglass.ico \
    ico/house.ico \
    ico/IE.ico \
    ico/info.ico \
    ico/key.ico \
    ico/kucun.ico \
    ico/laba.ico \
    ico/LED.ico \
    ico/left1.ICO \
    ico/mail add.ico \
    ico/mail delete.ico \
    ico/mail send.ico \
    ico/mail server.ico \
    ico/mail warning.ico \
    ico/mail.ico \
    ico/man.ICO \
    ico/MISC1059.ICO \
    ico/MSGBOX01.ICO \
    ico/MSGBOX02.ICO \
    ico/network computer.ico \
    ico/network.ico \
    ico/NOTE17.ICO \
    ico/oftenMessage.ico \
    ico/open mail.ico \
    ico/pospe.ico \
    ico/product.ico \
    ico/receive.ico \
    ico/record.ico \
    ico/right1.ICO \
    ico/RULERS.ICO \
    ico/send.ico \
    ico/set.ico \
    ico/Shut Down.ico \
    ico/SINEWAVE.ICO \
    ico/sound.ico \
    ico/student.ico \
    ico/toray.ico \
    ico/TRASH04A.ICO \
    ico/TRASH04B.ICO \
    ico/TRFFC09.ICO \
    ico/TRFFC10A.ICO \
    ico/TRFFC10B.ICO \
    ico/TRFFC10C.ICO \
    ico/up1.ICO \
    ico/W95MBX01.ICO \
    ico/W95MBX02.ICO \
    ico/W95MBX03.ICO \
    ico/W95MBX04.ICO \
    ico/women.ICO \
    ico/zoom in.ico \
    ico/zoom out.ico \
    img/猫.gif \
    img/桃华月惮[第4集].mp4_1396779771.gif \
    img/26100000008943144167993954076.jpg \
    img/bg.jpg \
    img/ic_launcher.jpg \
    img/psb.jpg \
    img/wallpaper1.jpg \
    img/wallpaper2.jpg \
    img/wallpaper3.jpg \
    img/wallpaper4.bmp.jpg \
    img/wallpaper5.jpg \
    img/wallpaper6.jpg \
    img/wallpaper7.jpg \
    img/wallpaper8.jpg \
    img/wallpaper9.jpg \
    img/wallpaper10.jpg \
    img/实验2.jpg \
    img/小女孩.jpg \
    img/exit.png \
    img/notify.png \
    img/user.png \
    img/猫.gif \
    img/桃华月惮[第4集].mp4_1396779771.gif \
    img/26100000008943144167993954076.jpg \
    img/bg.jpg \
    img/ic_launcher.jpg \
    img/psb.jpg \
    img/wallpaper1.jpg \
    img/wallpaper2.jpg \
    img/wallpaper3.jpg \
    img/wallpaper4.bmp.jpg \
    img/wallpaper5.jpg \
    img/wallpaper6.jpg \
    img/wallpaper7.jpg \
    img/wallpaper8.jpg \
    img/wallpaper9.jpg \
    img/wallpaper10.jpg \
    img/实验2.jpg \
    img/小女孩.jpg \
    img/exit.png \
    img/notify.png \
    img/user.png \
    ico/folder open.ico \
    ico/mail add.ico \
    ico/mail delete.ico \
    ico/mail send.ico \
    ico/mail server.ico \
    ico/mail warning.ico \
    ico/network computer.ico \
    ico/open mail.ico \
    ico/Shut Down.ico \
    ico/zoom in.ico \
    ico/zoom out.ico \
    ico/allBf.png \
    ico/allsb.png \
    ico/allWx.png \
    ico/ExBf.png \
    ico/ExSb.png \
    ico/ExWx.png \
    ico/Badd.png \
    ico/Sadd.png \
    ico/Wadd.png
