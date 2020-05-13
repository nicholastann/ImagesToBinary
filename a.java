 byte[] data = null;
        ByteArrayOutputStream bas = null;
        try {
            URL u = new URL(
                    "http://www.eso.org/public/archives/images/screen/eso0844a.jpg");
            HttpURLConnection con1 = (HttpURLConnection) u.openConnection();
            con1.setAllowUserInteraction(true);
            con1.setRequestMethod("GET");
            con1.connect();
            InputStream is = con1.getInputStream();
            BufferedImage imgToServe = null;
            if (is != null) {
                imgToServe = ImageIO.read(is);
            }
            bas = new ByteArrayOutputStream();
            ImageIO.write(imgToServe, "jpg", bas);

            File f = new File("C:\\img.jpg");
            ImageIO.write(imgToServe, "jpg", f);

            data = bas.toByteArray();
            String str = "";
            for (int i = 0; i < data.length; i++) {
                str = str + toBinary(data[i]);
            }
            System.out.println(str);

        } catch (HTTPException he) {

        } catch (IOException ioe) {
        }
    }

    private static String toBinary(byte b) {
        StringBuilder sb = new StringBuilder("00000000");

        for (int bit = 0; bit < 8; bit++) {
            if (((b >> bit) & 1) > 0) {
                sb.setCharAt(7 - bit, '1');
            }
        }
        return (sb.toString());
    }