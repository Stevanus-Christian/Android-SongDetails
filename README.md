# Android-SongDetails

Proyek ini dibuat sebagai tugas akhir untuk memenuhi persyaratan lulus matakuliah Pemrograman Aplikasi Bergerak Lanjutan kelas SI61 di Universitas Multi Data Palembang.

# API
### koneksi.php
```php
<?php
$host = "localhost";
$user = "id19029333_usermusik";
$pass = "St3v4nus@si61";
$db = "id19029333_dbmusik";

$konek = mysqli_connect($host, $user, $pass, $db) or die("MySQL Tidak Terhubung");
?>
```

### create.php
```php
<?php
require("koneksi.php");

if($_SERVER["REQUEST_METHOD"] == "POST"){
    $judul = $_POST["judul"];
    $penyanyi = $_POST["penyanyi"];
    $album = $_POST["album"];
    $aliran = $_POST["aliran"];
    
    $perintah = "INSERT INTO tblmusik(judul, penyanyi, album, aliran) VALUES('$judul', '$penyanyi', '$album', '$aliran')";
    $eksekusi = mysqli_query($konek, $perintah);
    $cek = mysqli_affected_rows($konek);
    
    if($cek > 0){
        $response["kode"] = 1;
        $response["pesan"] = "Sukses Menyimpan Data";
    }
    else{
        $response["kode"] = 0;
        $response["pesan"] = "Gagal Menyimpan Data";
    }
}
else{
    $response["kode"] = 0;
    $response["pesan"] = "Tidak Ada POST Data";
}

echo json_encode($response);
mysqli_close($konek);
```

### retrieve.php
```php
<?php
require("koneksi.php");

$perintah = "SELECT * FROM tblmusik";
$eksekusi = mysqli_query($konek, $perintah);
$cek = mysqli_affected_rows($konek);

if($cek > 0){
    $response["kode"] = 1;
    $response["pesan"] = "Data Tersedia";
    $response["data"] = array();
    
    while($get = mysqli_fetch_object($eksekusi)){
        $var["id"] = $get -> id;
        $var["judul"] = $get -> judul;
        $var["penyanyi"] = $get -> penyanyi;
        $var["album"] = $get -> album;
        $var["aliran"] = $get -> aliran;
        $var["created_at"] = $get -> created_at;
        $var["updated_at"] = $get -> updated_at;
        
        array_push($response["data"], $var);
    }
}
else{
    $response["kode"] = 0;
    $response["pesan"] = "Data Tidak Tersedia";
}

echo json_encode($response);
mysqli_close($konek);
```

### delete.php
```php
<?php
require("koneksi.php");
 
$response = array();
 
if($_SERVER['REQUEST_METHOD'] == 'POST'){
    $id = $_POST["id"];
    
    $perintah = "DELETE FROM tblmusik WHERE id='$id'";
    $eksekusi = mysqli_query($konek, $perintah);
    $cek = mysqli_affected_rows($konek);
    
    if($cek > 0){
        $response["kode"] = 1;
        $response["pesan"] = "Sukses Menghapus Data";
    }
    else{
        $response["kode"] = 0;
        $response["pesan"] = "Gagal Menghapus Data";
    }
}
else{
    $response["kode"] = 0;
    $response["pesan"] = "Tidak ada Hapus Data";
}
 
echo json_encode($response);
mysqli_close($konek);
```

### update.php
```php
<?php
require("koneksi.php");
 
$response = array();
 
if($_SERVER["REQUEST_METHOD"] == "POST"){
    $id = $_POST["id"];
    $judul = $_POST["judul"];
    $penyanyi = $_POST["penyanyi"];
    $album = $_POST["album"];
    $aliran = $_POST["aliran"];
    
    $perintah = "UPDATE tblmusik SET judul='$judul', penyanyi='$penyanyi', album='$album', aliran='$aliran' WHERE id='$id'";
    $eksekusi = mysqli_query($konek, $perintah);
    $cek = mysqli_affected_rows($konek);
    
    if($cek > 0){
        $response["kode"] = 1;
        $response["pesan"] = "Sukses Mengubah Data";
    }
    else{
        $response["kode"] = 0;
        $response["pesan"] = "Gagal Mengubah Data";
    }
}
else{
    $response["kode"] = 0;
    $response["pesan"] = "Tidak ada Post Data";
}
 
echo json_encode($response);
mysqli_close($konek);
?>
```
