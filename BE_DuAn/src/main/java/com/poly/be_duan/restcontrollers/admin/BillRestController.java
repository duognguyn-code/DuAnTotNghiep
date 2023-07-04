package com.poly.be_duan.restcontrollers.admin;

import com.github.sarxos.webcam.Webcam;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.multi.qrcode.detector.MultiDetector;
import com.google.zxing.qrcode.decoder.Decoder;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.poly.be_duan.entities.Bill;
import com.poly.be_duan.service.BillDetailService;
import com.poly.be_duan.service.BillService;
import com.poly.be_duan.service.CookieService;
import com.poly.be_duan.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/bill")
public class BillRestController {
    @Autowired
    BillService billService;

    @Autowired
    BillDetailService billDetailService;

    @Autowired
    CookieService cookieService;

    @Autowired
    ProductService productService;

    @GetMapping("")
    public ResponseEntity<List<Bill>> getAll() {
        System.out.println(billService.getAll());
        try {
            return ResponseEntity.ok(billService.getAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }


    }

    @GetMapping("/{id}")
    public List<Bill> getByID(@PathVariable(value = "id") Integer id) {
//        cookieService.remove("idBill");
//        cookieService.add("idBill", id, 1);
//        int a = Integer.parseInt(id);
        return billService.getBill(id);

    }

    @GetMapping("/{phone}/{sts}/date")
    public ResponseEntity<List<Bill>> searchBill(@RequestParam("date1") String date1, @RequestParam("date2") String date2, @PathVariable(value = "phone") String phone, @PathVariable(value = "sts") String sts) throws ParseException,Exception {

        Webcam webcam = Webcam.getDefault();
        webcam.close();
        webcam.setViewSize(new Dimension(640, 480));
        webcam.open();
//-------------
//        String data = "01";
//        String path = "C:\\Users\\Windows\\Pictures\\Saved Pictures\\codeqr.jpg";
//        try {
//            BitMatrix matrix = new MultiFormatWriter()
//                    .encode(data, BarcodeFormat.QR_CODE, 500, 500);
//            MatrixToImageWriter.writeToPath(matrix, "jpg", Paths.get(path));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//-----------
        try {
            ImageIO.write(webcam.getImage(), "PNG", new File("C:\\Users\\Windows\\Desktop\\QRCODE.png"));
            String filePath = "C:\\Users\\Windows\\Desktop\\QRCODE.png";
            String charset = "UTF-8"; // or "ISO-8859-1"
            Map hintMap = new HashMap();

            BufferedImage image = webcam.getImage();
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(image, "PNG", os);
            InputStream is = new ByteArrayInputStream(os.toByteArray());
            BinaryBitmap binaryBitmap = new BinaryBitmap(
                    new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(is))));


            BitMatrix bm = binaryBitmap.getBlackMatrix();

            MultiDetector detector = new MultiDetector(bm);
            DetectorResult dResult = detector.detect();
            BitMatrix QRImageData = null;
            if (dResult != null) {
                 QRImageData = dResult.getBits();
                hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
//                System.out.println("Data read from QR Code: " + readQRCode(filePath, charset, hintMap, QRImageData));
//                System.out.println("Data read from QR Code: " + readQRCode(filePath, charset, hintMap, QRImageData));
            String a = readQRCode(filePath, charset, hintMap, QRImageData);
                System.out.println(a+"sssss");
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("Unable to read Qr code: Please dont shake your mobile!");
            // e.printStackTrace();
        } catch (NotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("Unable to read Qr code: Please dont shake your mobile!");
        } catch (FormatException e) {
            // TODO Auto-generated catch block
            System.out.println("Unable to read Qr code: Please dont shake your mobile!");
        }
            return null;


    }
    public static String readQRCode(String filePath, String charset, Map hintMap, BitMatrix qRImageData)
            throws FileNotFoundException, IOException, NotFoundException, FormatException {
        BinaryBitmap binaryBitmap = new BinaryBitmap(
                new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(new FileInputStream(filePath)))));
        Decoder dr = new Decoder();
        DecoderResult qrCodeResult = null;
        try {
            qrCodeResult = dr.decode(qRImageData);
        } catch (ChecksumException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return qrCodeResult.getText();
    }
    @PutMapping("/updateStatus")
    public Bill updateStatus(@RequestBody Bill bill) {
        Bill billOld = billService.findBillByID(bill.getId()).get();

        System.out.println(billOld.getId() + "ssss");
        System.out.println(billService.updateStatus(billOld));
        if (bill.getStatus() < billOld.getStatus()) {
            return null;
        } else {
            billOld.setStatus(bill.getStatus());
            return billService.updateStatus(billOld);
        }

    }
}
