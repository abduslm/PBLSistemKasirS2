Sebelum menjalankan aplikasi, Konfigurasi terlebih dahulu file config.properties
untuk default konfigurasinya yakni seperti berikut:

#untuk konfigurasi url database
db.url=jdbc:mysql://localhost:3306/

#untuk konfigurasi nama database
db.namadb=temeji

#untuk konfigurasi user database
db.user=root

#untuk konfigurasi password user database
db.password=



#Printer Config
# untuk konfigurasi nama printer dapur dan kasir
#printer.dapur=nama printer (printer.dapur=OFF #untuk konfigurasi mematikan printer)
printer.dapur=OFF
printer.kasir=OFF

# konfigurasi nama file jasper untuk struk dapur dan kasir
#(gunakan separator "/" untuk pemisahnya)
printer.dapur.struk=C:/Program Files/POS_KedaiTemeji/Dapur.jasper 
printer.kasir.struk=C:/Program Files/POS_KedaiTemeji/Kasir.jasper

# nama parameter pada file struk jasper (printer.parameter.nama=Param)
printer.parameter.nama=Param



