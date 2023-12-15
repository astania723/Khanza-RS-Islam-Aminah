<?php
    include '../conf/conf.php';
    include '../phpqrcode/qrlib.php'; 
    
    $sepbpjs = validTeks(str_replace("_"," ",$_GET['sepbpjs']));
    if(isset($sepbpjs)){
        $PNG_TEMP_DIR   = dirname(__FILE__).DIRECTORY_SEPARATOR.'temp'.DIRECTORY_SEPARATOR;
        $PNG_WEB_DIR    = 'temp/';
        if (!file_exists($PNG_TEMP_DIR)) mkdir($PNG_TEMP_DIR);
        $filename              = $PNG_TEMP_DIR.$sepbpjs.'.png';
        $errorCorrectionLevel  = 'L';
        $matrixPointSize       = 4;
        $setting               = mysqli_fetch_array(bukaquery("select nama_instansi,kabupaten from setting"));
        QRcode::png("".getOne("select no_sep from bridging_sep where no_sep='$sepbpjs'"), $filename, $errorCorrectionLevel, $matrixPointSize, 2);
    }   
?>  