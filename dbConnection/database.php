<?php
class Database{
 
    // specify your own database credentials
    private $host = "mysql-instance1.cqilpo3gbwln.us-east-1.rds.amazonaws.com";
    private $db_name = "seeye";
    private $username = "Administrator";
    private $password = "seeyetech";
    public $conn;
 
    // get the database connection
    public function getConnection(){
 
        $this->conn = null;
 
        try{
            $this->conn = new PDO("mysql:host=" . $this->host . ";dbname=" . $this->db_name, $this->username, $this->password);
            $this->conn->exec("set names utf8");
        }catch(PDOException $exception){
            echo "Connection error: " . $exception->getMessage();
        }
 
        return $this->conn;
    }
}

// mysql> show databases;
// +--------------------+
// | Database           |
// +--------------------+
// | information_schema |
// | innodb             |
// | mysql              |
// | performance_schema |
// | seeye              |
// | sys                |
// +--------------------+
// 6 rows in set (0.07 sec)
// mysql> show tables;
// +-----------------+
// | Tables_in_seeye |
// +-----------------+
// | product         |
// | retailer        |
// +-----------------+
// 2 rows in set (0.05 sec)
// mysql> select * from product
//     -> ;
// +-----------+----------+-----------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------+---------+---------------+-----------+--------+--------+
// | ProductId | Category | Name                                          | Image                                                                                                                                      | Channel | Show          | Timestamp | Rating | Price  |
// +-----------+----------+-----------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------+---------+---------------+-----------+--------+--------+
// |         1 | Rug      | Lodge Hand- Woven Area Rug                    | https://secure.img1-fg.wfcdn.com/im/59171037/resize-h800%5Ecompr-r85/2314/23145933/Lodge+Hand-Woven+Flatweave+Wool+Red/Orange+Area+Rug.jpg | HGTV    | House Hunters |           | 5      | 56.99  |
// |         2 | Rug      | Hand Tufted Green Area Rug                    | https://secure.img1-fg.wfcdn.com/im/24617800/resize-h800%5Ecompr-r85/3063/30632638/Hand-Tufted+Green+Area+Rug.jpg                          | HGTV    | House Hunters |           | 5      | 62.99  |
// |         3 | Table    | Alsey Rustic Teak and 4 Drawer Accent Chest   | https://secure.img1-fg.wfcdn.com/im/38774897/resize-h800%5Ecompr-r85/4381/43817957/Alsey+Rustic+Teak+and+4+Drawer+Accent+Chest.jpg         | HGTV    | House Hunters |           | 5      | 346.99 |
// |         4 | Table    | Idella Side Table                             | https://secure.img1-fg.wfcdn.com/im/13525664/resize-h800%5Ecompr-r85/3932/39325902/Idella+Side+Table.jpg                                   | HGTV    | House Hunters |           | 4.8    | 196.99 |
// |         5 | Paint    | Spiced Cedar                                  | https://www.hgtv.com/content/dam/images/hgtv/unsized/2018/10/17/rx_dh19_HGSW-SW7702-spiced-cider.jpg                                       | HGTV    | House Hunters |           | 4      | 3.99   |
// |         6 | Floor    | Virginia Mill Works Engineered 9/16" x 7-1/2" | https://lumberliquidators.scene7.com/is/image/LumberLiquidators/10043736_sw?$700x700$                                                      | HGTV    | House Hunters |           | 4.7    | 0      |
// |         7 | Chair    | Irongate Armchair                             | https://secure.img1-fg.wfcdn.com/im/75777552/resize-h800%5Ecompr-r85/4592/45922179/Irongate+Armchair.jpg                                   | HGTV    | House Hunters |           | 4.8    | 799.99 |
// +-----------+----------+-----------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------+---------+---------------+-----------+--------+--------+
// 7 rows in set (0.05 sec)
// mysql> select * from retailer
//     -> ;
// +-----------+----------+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
// | ProductId | Retailer | Link                                                                                                                                                                                                     |
// +-----------+----------+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
// |         1 | NULL     | https://www.allmodern.com/rugs/hd0/lodge-hand-woven-flatweave-wool-redorange-area-rug-l167-k~qcr1149.html?refid=GX270327796458-QCR1149&device=c&ptid=303485575812&targetid=pla-303485575812&network=g&ir |
// |         1 | NULL     | https://www.birchlane.com/rugs-windows/hd0/lodge-hand-woven-flatweave-wool-redorange-area-rug-l167-k~qcr1149.html?refid=GX191388243082-QCR1149_15494683&device=c&ptid=303485575852&network=g&ireid=23145 |
// |         1 | NULL     | https://www.wayfair.com/rugs/hd0/lodge-hand-woven-flatweave-wool-redorange-area-rug-l167-k~qcr1149.html?refid=GX170140882782-QCR1149_15494683&device=c&ptid=277586174335&network=g&targetid=pla-27758617 |
// |         2 | NULL     | https://www.wayfair.com/rugs/pdp/wildon-home-hand-tufted-green-area-rug-cst42365.html?piid=17503585                                                                                                      |
// |         3 | NULL     | https://www.wayfair.com/furniture/pdp/foundry-select-alsey-rustic-teak-and-4-drawer-accent-chest-fnds1469.html                                                                                           |
// |         4 | NULL     | https://www.wayfair.com/outdoor/pdp/union-rustic-idella-side-table-unrs1441.html                                                                                                                         |
// |         5 | NULL     | https://shop.samplize.com/products/spiced-cider-7702-12x12?variant=15956801060966&gclid=Cj0KCQiA4aXiBRCRARIsAMBZGz86k_kOdLlXvGpyznFg4v5bJPYrwTu_SH3PqB_PkFD84eKXu7SMCfoaAnPGEALw_wcB                     |
// |         5 | NULL     | https://www.lowes.com/pd/HGTV-HOME-by-Sherwin-Williams-Spiced-Cider-Interior-Paint-Sample-Actual-Net-Contents-8-fl-oz/1000278445?cm_mmc=SCE_PLA-_-Paint-_-InteriorPaint-_-1000278445:HGTV_HOME_by_Sherwi |
// |         5 | NULL     | https://www.walmart.com/ip/Glidden-One-Coat-Interior-Paint-Primer-Spiced-Cider/778131421?wmlspartner=wlpa&selectedSellerId=0&adid=22222222227158289630&wl0=&wl1=g&wl2=c&wl3=268021076833&wl4=pla-4429699 |
// |         6 | NULL     | https://www.lumberliquidators.com/ll/c/Canterbury-Hickory-Virginia-Mill-Works-Engineered-WBCBH7/10043736#image-3                                                                                         |
// |         7 | NULL     | https://www.allmodern.com/furniture/hd0/irongate-armchair-l54-k~snpn4761.html?refid=GX170365361970-SNPN4761&device=c&ptid=18283950120&targetid=pla-18283950120&network=g&ireid=45922178&gclid=Cj0KCQiA4a |
// |         7 | NULL     | https://www.wayfair.com/furniture/hd0/irongate-armchair-l54-k~snpn4761.html?refid=GX224748458851-SNPN4761&device=c&ptid=403349941098&network=g&targetid=pla-403349941098&channel=GooglePLA&ireid=4592217 |
// +-----------+----------+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
// 12 rows in set (0.05 sec)
?>