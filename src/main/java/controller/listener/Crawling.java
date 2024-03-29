package controller.listener;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import model.dao.ProductDAO;
import model.dto.ProductDTO;

public class Crawling {
	
	public void crawling() {
		String[] products = {
				"omega3",
				"milkthistle",
				"iron-chewable",
				"calcium-magnesium-vitamind",
				"vitaminb",
				"collagen",
				"probiotics",
				"lutein",
				"vitaminc",
				"hyaluronicacid-spirulina",
		};
		int PK = 1001;
		String imgPath;
		
		for (String product : products) {
			imgPath = downloadPhoto("https://pilly.kr/product/", product, PK++);
			scrapPage("https://pilly.kr/product/" + product, PK, imgPath);
		}
	}

	private String downloadPhoto(String url, String productName, int pk) {
		Document doc = null;
		String path = "";
		
		
		try {
			doc = Jsoup.connect(url + productName).get();

			Elements elems = doc.select("li.has-image img[src]");
			String src = "";
			for (Element elem : elems) {
				System.out.println(elem.attr("src"));
				src = elem.attr("src");
				break;
			}
			
//			String currentDirectory = System.getProperty("user.dir");
//			System.out.println("현재 작업 디렉토리: " + currentDirectory);

			URL u = new URL(src);
			InputStream is = u.openStream();
			path = "src/main/webapp/img/" + productName + ".jpg";
//			path = "img/" + productName + ".jpg";

			FileOutputStream fos = new FileOutputStream(path);
			int b;
			while ((b = is.read()) != -1) {
				fos.write(b);
			}
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

        System.out.println(path + " :: 작업 완료");
        return "img/" + productName + ".jpg";
		
	}
	
	private void scrapPage(String url, int pk, String imgPath) {

		Document doc = null;

		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		ProductDTO pDTO = new ProductDTO();
		pDTO.setSearchCondition("상품추가");
		
		// PID
		// DB에서 넣어줌
//		pDTO.setPID(pk);

		// pDetail
		Elements elems = doc.select("div.description");
		Iterator<Element> itr = elems.iterator();
		if (itr.hasNext()) {
//			System.out.println("div.desctiption: " + itr.next().text());
			pDTO.setpDetail(itr.next().text());
		}
		
		// costPrice
		// regularPrice
		// sellingPrice
		elems = doc.select("div.price"); // "13,500원"
		itr = elems.iterator();
		if (itr.hasNext()) {
			String strPrice = itr.next().text();
			if (strPrice.contains("%")) {
				strPrice = strPrice.split(" ")[2];
			}
			strPrice = strPrice.substring(0, strPrice.length() - 1);
			strPrice = strPrice.replace(String.valueOf(','), "");
			System.out.println(strPrice);
			int selling = Integer.parseInt(strPrice);
//			int selling = 3000;
			pDTO.setCostPrice(selling + 2000);
			pDTO.setRegularPrice(selling + 1000);
			pDTO.setSellingPrice(selling);
		}

		// pQty
		pDTO.setpQty(30);

		// regTime : 현재 시간
		// DB에서 넣어줌
//		pDTO.setRegTime(Timestamp.from(Instant.now()));
		
		// imagePath
		pDTO.setImagePath(imgPath);
		
		// sellingState
		pDTO.setSellingState("판매중");
		
		elems = doc.select("dd");
		itr = elems.iterator();
		int cnt = 0;
		while (itr.hasNext()) {
			Element e = itr.next();
			
			if (cnt == 0) {

				// pName
				String pName = e.text(); // 필리 오메가3
				String[] strs = pName.split("\\s+");
				if (strs.length > 1) {
					pName = strs[1];
				}
				pDTO.setpName(pName);

			} else if (cnt == 3) {

				// exp
				String exp = e.text().split(" /")[0];
				pDTO.setExp(exp);

			} else if (cnt == 6) {

				String str = e.text();

				// usage
				String[] strs = str.split(" 1일 섭취량 당 함량 : ");
				String usage = "";
				strs = strs[0].split("1일 섭취량 : ");
				if (strs.length > 1) {
					usage = strs[1];
				} else {
					usage = "1캡슐";
				}
				pDTO.setUsage(usage);

				// ingredient
				if (strs.length > 1) {
					str = strs[1];
				}
				strs = str.split(" ※ ");
				str = strs[0];
				pDTO.setIngredient(str);

			} else if (cnt == 7) {

				// category
				String str = e.text();
				if (str.contains("눈")) {
					pDTO.setCategory("눈");
				} else if (str.contains("간")) {
					pDTO.setCategory("간");
				} else if (str.contains("뼈")) {
					pDTO.setCategory("뼈/치아");
				} else if (str.contains("에너지")) {
					pDTO.setCategory("활력");
				} else if (str.contains("스트레스") || str.contains("면역")) {
					pDTO.setCategory("면역");
				} else if (str.contains("기억력")) {
					pDTO.setCategory("두뇌");
				} else if (str.contains("피부")) {
					pDTO.setCategory("피부");
				} else if (str.contains("유산균")) {
					pDTO.setCategory("소화");
				}
			}

			cnt++;
		}
		System.out.println(pDTO);
		ProductDAO pDAO = new ProductDAO();
		if (pDAO.insert(pDTO)) {
			System.out.println("크롤링 성공!!");
		} else {
			System.out.println("크롤링 실패...");
		}
	}
}

