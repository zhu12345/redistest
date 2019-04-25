package cn.redis.test;

import cn.util.shell.SSH2Utils;

import java.io.IOException;

public class Test31 {
    public static void main(String[] args) {
        try {
//            SSH2Utils ssh2Utils = new SSH2Utils("192.168.13.99", "ubuntu", "1qazxsw2", 22, "/home/ubuntu/");
//            ssh2Utils.putFile("C:\\huadan\\hws_adapter\\udr\\pricedata\\CT_HW_VM_20190111154959.csv", "CT_HW_VM_20190111154959.csv");
//            ssh2Utils.downLoadFile("C:\\huadan\\hws_adapter\\udr\\pricedata\\a.txt", "a.txt");
//            SSH2Utils ssh2Utils = new SSH2Utils("10.144.242.136", "ubuntus", "bss@cdr30530", 22, "/home/ubuntus/cdr_adapter/oos/downloadbak/");
//            ssh2Utils.downLoadFile("C:\\huadan\\CT_OOS_OOS_20190115012497.csv", "CT_OOS_OOS_20190115012497.csv");
            String[] s = new String[]{
                    "CTC_cn-gz1_bandwidthtr_20190322142818_158.csv",
                    "CTC_cn-gz1_bandwidthtr_20190322152237_318.csv",
                    "CTC_cn-gz1_bandwidthtr_20190322162913_933.csv",
                    "CTC_cn-gz1_bandwidthtr_20190322172832_939.csv",
                    "CTC_cn-gz1_bandwidthtr_20190322183157_237.csv",
                    "CTC_cn-gz1_bandwidthtr_20190322194948_465.csv",
                    "CTC_cn-gz1_bandwidthtr_20190322203449_773.csv",
                    "CTC_cn-gz1_bandwidthtr_20190322214959_411.csv",
                    "CTC_cn-gz1_bandwidthtr_20190322225005_185.csv",
                    "CTC_cn-gz1_bandwidthtr_20190322232847_258.csv",
                    "CTC_cn-gz1_bandwidthtr_20190323005005_987.csv",
                    "CTC_cn-gz1_bandwidthtr_20190323013138_685.csv",
                    "CTC_cn-gz1_bandwidthtr_20190323023945_733.csv",
                    "CTC_cn-gz1_bandwidthtr_20190323033440_884.csv",
                    "CTC_cn-gz1_bandwidthtr_20190323044856_002.csv",
                    "CTC_cn-gz1_bandwidthtr_20190323052524_892.csv",
                    "CTC_cn-gz1_bandwidthtr_20190323061832_598.csv",
                    "CTC_cn-gz1_bandwidthtr_20190323074957_880.csv",
                    "CTC_cn-gz1_bandwidthtr_20190323081908_521.csv",
                    "CTC_cn-gz1_bandwidthtr_20190323092629_882.csv",
                    "CTC_cn-gz1_bandwidthtr_20190323102828_238.csv",
                    "CTC_cn-gz1_bandwidthtr_20190323114424_939.csv",
                    "CTC_cn-gz1_bandwidthtr_20190323122858_461.csv",
                    "CTC_cn-gz1_bandwidthtr_20190323132054_050.csv",
                    "CTC_cn-gz1_bandwidthtr_20190323142718_888.csv",
                    "CTC_cn-gz1_bandwidthtr_20190323152626_256.csv",
                    "CTC_cn-gz1_bandwidthtr_20190323163339_393.csv",
                    "CTC_cn-gz1_bandwidthtr_20190323174954_340.csv",
                    "CTC_cn-gz1_bandwidthtr_20190323183607_582.csv",
                    "CTC_cn-gz1_bandwidthtr_20190323192630_085.csv",
                    "CTC_cn-gz1_bandwidthtr_20190323204937_726.csv",
                    "CTC_cn-gz1_bandwidthtr_20190323212505_528.csv",
                    "CTC_cn-gz1_bandwidthtr_20190323224955_508.csv",
                    "CTC_cn-gz1_bandwidthtr_20190324000038_819.csv",
                    "CTC_cn-gz1_bandwidthtr_20190324003713_195.csv",
                    "CTC_cn-gz1_bandwidthtr_20190324012627_400.csv",
                    "CTC_cn-gz1_bandwidthtr_20190324023953_544.csv",
                    "CTC_cn-gz1_bandwidthtr_20190324034942_388.csv",
                    "CTC_cn-gz1_bandwidthtr_20190324044940_204.csv",
                    "CTC_cn-gz1_bandwidthtr_20190324053528_488.csv",
                    "CTC_cn-gz1_bandwidthtr_20190324064956_992.csv",
                    "CTC_cn-gz1_bandwidthtr_20190324072414_295.csv",
                    "CTC_cn-gz1_bandwidthtr_20190324084936_669.csv",
                    "CTC_cn-gz1_bandwidthtr_20190324092519_108.csv",
                    "CTC_cn-gz1_bandwidthtr_20190324102525_785.csv",
                    "CTC_cn-gz1_bandwidthtr_20190324114904_207.csv",
                    "CTC_cn-gz1_bandwidthtr_20190324123444_478.csv",
                    "CTC_cn-gz1_bandwidthtr_20190324133501_599.csv",
                    "CTC_cn-gz1_bandwidthtr_20190324143746_576.csv",
                    "CTC_cn-gz1_bandwidthtr_20190324152428_610.csv",
                    "CTC_cn-gz1_bandwidthtr_20190324164419_366.csv",
                    "CTC_cn-gz1_bandwidthtr_20190324172733_227.csv",
                    "CTC_cn-gz1_bandwidthtr_20190324183525_747.csv",
                    "CTC_cn-gz1_bandwidthtr_20190324194934_955.csv",
                    "CTC_cn-gz1_bandwidthtr_20190324202751_959.csv",
                    "CTC_cn-gz1_bandwidthtr_20190324212735_645.csv",
                    "CTC_cn-gz1_bandwidthtr_20190324224904_735.csv",
                    "CTC_cn-gz1_bandwidthtr_20190324232822_822.csv",
                    "CTC_cn-gz1_bandwidthtr_20190325003610_175.csv",
                    "CTC_cn-gz1_bandwidthtr_20190325014908_007.csv",
                    "CTC_cn-gz1_bandwidthtr_20190325024014_741.csv",
                    "CTC_cn-gz1_bandwidthtr_20190325032529_308.csv",
                    "CTC_cn-gz1_bandwidthtr_20190325043425_680.csv",
                    "CTC_cn-gz1_bandwidthtr_20190325052527_775.csv",
                    "CTC_cn-gz1_bandwidthtr_20190325064232_426.csv",
                    "CTC_cn-gz1_bandwidthtr_20190325075003_187.csv",
                    "CTC_cn-gz1_bandwidthtr_20190325084942_853.csv",
                    "CTC_cn-gz1_bandwidthtr_20190325100111_898.csv",
                    "CTC_cn-gz1_bandwidthtr_20190325103948_162.csv",
                    "CTC_cn-gz1_bandwidthtr_20190325114022_952.csv",
                    "CTC_cn-gz1_bandwidthtr_20190325124223_577.csv",
                    "CTC_cn-gz1_bandwidthtr_20190325135009_093.csv",
                    "CTC_cn-gz1_bandwidthtr_20190325144949_084.csv",
                    "CTC_cn-gz1_bandwidthtr_20190325152537_893.csv",
                    "CTC_cn-gz1_bandwidthtr_20190325161912_588.csv",
                    "CTC_cn-gz1_bandwidthtr_20190325174930_229.csv",
                    "CTC_cn-gz1_bandwidthtr_20190325182731_114.csv",
                    "CTC_cn-gz1_bandwidthtr_20190325192644_957.csv",
                    "CTC_cn-gz1_bandwidthtr_20190325202749_496.csv",
                    "CTC_cn-gz1_bandwidthtr_20190325212742_345.csv",
                    "CTC_cn-gz1_bandwidthtr_20190325222558_350.csv",
                    "CTC_cn-gz1_bandwidthtr_20190325232739_216.csv",
                    "CTC_cn-gz1_bandwidthtr_20190326003034_466.csv"
            };
            /*for (String fileName : s) {
                SSH2Utils ssh2Utils = new SSH2Utils("42.123.76.15", "logcheck", "75PCKyQmQBbsCZYxRzdVWd%hf%R@ZB", 22, "/backup1/110_hws_adapter/cdr_file_backup/rawdata/");
                ssh2Utils.downLoadFile("C:\\Users\\22984\\Desktop\\新建文件夹\\", fileName);
            }*/
            /*for (String fileName : s) {
                SSH2Utils ssh2Utils = new SSH2Utils("42.123.76.15", "logcheck", "75PCKyQmQBbsCZYxRzdVWd%hf%R@ZB", 22, "/home/logcheck/");
                ssh2Utils.putFile("C:\\Users\\22984\\Desktop\\新建文件夹\\"+ fileName);
            }*/
            for (String fileName : s) {
                SSH2Utils ssh2Utils = new SSH2Utils("10.144.242.110", "ctyuns", "ctyun@2017", 22, "/cdr_file_backup/udr/outdata/");
//                System.out.println(ssh2Utils.doShell("ls", "\n"));;
                ssh2Utils.downLoadFile("C:\\Users\\22984\\Downloads\\新建文件夹\\a\\", fileName);
            }
            /*for (String fileName : s) {
                SSH2Utils ssh2Utils = new SSH2Utils("36.111.164.141", "root", "gQ4^tqabfb", 22, "/root/data/");
                ssh2Utils.putFile("C:\\Users\\22984\\Desktop\\新建文件夹\\" + fileName);
            }
            for (String fileName : s) {
                File file = new File("C:\\Users\\22984\\Desktop\\新建文件夹\\" + fileName);
                if (file.exists()) {
                    file.delete();
                }
            }*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
