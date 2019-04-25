package cn.redis.test;

import ch.ethz.ssh2.Session;
import cn.constant.ConstantKey;
import cn.hws.bo.ZoneConfig;
import cn.temp.GetVMMsg;
import cn.util.httpUtil.HttpRestImpl;
import cn.util.httpUtil.dao.HttpRest;
import cn.util.shell.ShellOperateUtils;
import cn.util.string.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestMain {
    public static void main(String[] args) {/*
        InputStream stdout = null;
        Session session = null;
        StringBuffer stringBuffer = null;
        try {
            session = ShellOperateUtils.getSession(ConstantKey.userName, ConstantKey.password,
                    ConstantKey.host, ConstantKey.port);
            String cmd = "cd /cdr_file_backup/udr/rawdata;stat CTC_cn-ahwh1_20180912000350.zip";
            stdout = ShellOperateUtils.getInputStream(session, cmd);
            String split = "\\|";
            stringBuffer = ShellOperateUtils.doShell(stdout, "|");
            String[] strings = stringBuffer.toString().split("\\|");
            Map<String, String> map = new HashMap<String, String>();
            for (String s:strings) {
//                System.out.println(s);
                String[] a = s.replace(" ","").split("\\t");
                for (String s1:a) {
                    if (!StringUtils.pdNULL(s1) && s1.contains(":")) {
                        String[] strings1 = s1.split("\\:");
//                        System.out.println(s1);
                        String key = "";
                        String value = "";
                        for (int i = 0; i < strings1.length; i++) {
                                if (i==0) {
                                    key=strings1[i];
                                }else {
                                    value=value+strings1[i];
                                }
                        }
                        map.put(strings1[0], value);
                    }
                }
            }
            *//*Iterator<Map.Entry<String, String>> iterator =  map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> m = iterator.next();
                System.out.println(m.getKey()+":"+m.getValue());

            }*//*
            String size = map.get("Size");
            String accessDate = map.get("Access").substring(0,16);
            DateFormat dateFormat =  new SimpleDateFormat("yyyy-MM-ddHHmmss");
            Date date = dateFormat.parse(accessDate);
            System.out.print(date);
            System.out.print(Integer.parseInt(size));
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            ShellOperateUtils.closeStream(stdout);
            ShellOperateUtils.closeSession(session);
        }*/
        HttpRest httpRest = new HttpRestImpl();
        /*Map<String, String> mapHeader = new HashMap<String, String>();
        mapHeader.put("name", "18956674880");
        httpRest.getParamAsMap("http://10.144.242.126:80/hwservice/queryDomainByName", null,mapHeader);*/
        String[] strings = new String[]{"00d6809521e545a7ad03d290434a48fe",
                "03d982afda45492e89ff9b15746dfede",
                "0c361fff999347b4bcc1edac3de18fc6",
                "0f85931ab1a1492f8ae63e1f40f8441a",
                "1530870e811c4551921f02c3f7ce06a0",
                "18bcff0587274934b57458a498aedf88",
                "1a7583d70f034dc2be60ed9e836f68f8",
                "1e7cf69958eb49189728c782e0e098b1",
                "1ebfb6ec9c4e40fd93294340b5cadb81",
                "27a28a53405f403da0124825b443dd73",
                "2b87fff111e54047a660215f5db43817",
                "2be1d71d01ce4eb5896c2882e6f3b206",
                "2e64a73711bd43498f119250e4f173f6",
                "301490aead2944248c6fefb5a8e856f6",
                "42fa278e82614451b949c1045697ac58",
                "4eef13af2ac048f6a1da5c433331db2a",
                "4f5eccca0d014858868a15827f0cdff6",
                "5036f0da427c462fad253543bed970b3",
                "534ce1510e104d1bb6cbf14804926d97",
                "5417c2fd13a84e9d958893ffe5499ec7",
                "582713f5bc7644cabea5e0e2509e10cd",
                "5c3056eee20442fa9cfdd83fdfc57bcd",
                "60f40aec2ae14ba981cbf285dd80a5f7",
                "69730b1f24c6459e9c3c8dcfbf1a0cb5",
                "6d87444e4eb04582ae064be953cdff87",
                "80e1f48cfb8f4030a8e549b55f1fe43d",
                "8b8309ec93a7466e8d04429c18166630",
                "8bb7981b631e4eb18ec6e80570258714",
                "8c4b3f9d7654482eabf7e717cf06173d",
                "9550ac447cd64f059fafe543a661c360",
                "969303cf284c463f8345a1773442da75",
                "99d67aadaf5740898dd7761bce2bbaac",
                "a89423f9af3a4d53bf01d71e42834ab6",
                "aea374f735a646c390792fd91fd9d848",
                "aef6e6c91f1e449e9afb9ce3c9867f19",
                "b4923b0a4c07457e89e132de9ace6ceb",
                "b51fd5b6b8d84e9fafd30d9c3d2849c3",
                "c79a0dd77e7240c58d624aeed2d16cc3",
                "d28effee9c7b481ebf6205c55c911b35",
                "d5231747ea4a4b9da14538b737b59767",
                "da30f7f5112e45d1a594fc400be17385",
                "df5bca7d6b6440f086d0133e0e695159",
                "e6bdcf26488840aca52beb2693a8d104",
                "eb4061f9aa234b4b827ce157edfd03aa",
                "ec2385d2e3534ceb821704c308a440e7",
                "fe6425d76da746a8b421f7d3509ebb89",
                "cf35904fc3a74d1d9709b156acabfb46",
                "127798e2a4eb4a08bd0bd254f5e01df8",
                "ef2fff9d5af748d1895b8db7ef2f2455",
                "14b43da8b3974bdbb9c56cf05ab55f2b",
                "497105078561459a9e5f50763b8723d4",
                "fc1c86b77a614c958d46ad53ac5ae089",
                "6f90711ba00640529ead96b202774b6e",
                "25fc25bcf9464e49b302e20670e5fdff",
                "0357ae1db53f4b2aaad660757c3d0b25",
                "0a9396c1b66e40b4a8022af4f8d9eab5",
                "156238e403af472dab5a991338b99f77",
                "17349cefbbcb441a83168d370caf19a2",
                "21f0ba7e212843cca85845325368e1b1",
                "231a0c057b3f4825bc0a0deed18e2229",
                "5402986275634ca58dbe8f710a0be632",
                "542c4bbbfaa645b7878cc67baa082442",
                "54dac480e9f04ad3b7c4e37237879cf1",
                "55f519fe366f4fa38ffc02f2bb255689",
                "5a3cebb93f354115954570be2e1a51f6",
                "5ddfb006e4fe419dbf2f5159c45f32a2",
                "5ebc7c727fed46f0a0a790f2f0514e7e",
                "635b69680d7647bba521153eef3641f7",
                "723d3b1418ca4cee930636a189c601f9",
                "79cdfdb50b46435b88c53919ab3e39d8",
                "7ae4dbbab1c44af6b0ec1f058cc971ab",
                "7e9605e32d3b4ca4b78bf5144f11f201",
                "867e7d6d04f045c7aabb64a0363ce8ba",
                "98484b9c0aef436980ba728b843879d8",
                "9d358128bc8a4f789b26e66220e6029d",
                "9df01f0a3d5841128c4f1022659da4ca",
                "a3d557c575c94768a1033d56136f371a",
                "a5fc5e53b4784a9a9bca14c7d545120c",
                "adcfc129fb964a19abc06f0496f70290",
                "b604af914cab4130b992f8782a0bbd3e",
                "b758bd8fbeb045248bd2beaae442d825",
                "be16b1ebe5304decae270dc38d007c93",
                "c0c73a36cd65469a8478cdaf8a471e6d",
                "c563858395704423b3df164098d65b17",
                "cadd0a9ab7954cbbaaf9e520608e4edc",
                "d0cdda505f814abd93e12510a519c320",
                "d44b68b398a64f7ba9aef743c1d8b3a1",
                "d5634e1cefde4519b9f2fa8e7e10abcb",
                "d83e0f937f4041b1bca07c9be4852cac",
                "e27f581a301f4948a97e6efe3f18326f",
                "e6fb3586ff424c9c93af7c60f879a4ae",
                "efaf18c03abf496e996cc38429699c9b",
                "f65ccf218fdd4c458e8acf3d4cb4b4c2",
                "fbfd264dcb94410cae67475d44e64dfa",
                "ff04c3d32280483a87f227313486f951",
                "1eb84ab02f174c4bb6680122a9d0a596",
                "d4c50c34e3b2444baef37d191286a8da",
                "c1004a5d557c4d7e916a9babc8cfa7a3",
                "fc2937c84e294ee29011212ee7d1258f",
                "7588a59dda184074a1e2758e10f79682",
                "2f3be924beb642db88fac415d9f60d80"};
        for (String s:strings) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("masterResourceId", s);
             httpRest.postParamAsMap("http://10.144.242.126:80/vmservice/order/doDestroyByMasterResourceId", map, null, null);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
