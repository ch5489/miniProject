package com.model2.mvc.view.product;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.impl.ProductDaoImpl;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class AddProductAction extends Action {

//	public String execute (HttpServletRequest request, HttpServletRequest response) throws Exception 
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		if (FileUpload.isMultipartContent(request)) {
			System.out.println("=========1========");
			String temDir2 = "/Users/ssg/miniProject/01.Model2MVCShop(stu)/src/main/webapp/images/uploadFiles";
			System.out.println("=========2========");
			DiskFileUpload fileUpload = new DiskFileUpload();
			fileUpload.setRepositoryPath(temDir2);
			fileUpload.setSizeMax(1024 * 1024 * 100);
			fileUpload.setSizeThreshold(1024 * 100);

			if (request.getContentLength() < fileUpload.getSizeMax()) {
				Product product = new Product();
				System.out.println("=========3========");
				StringTokenizer token = null;

				List fileItemList = fileUpload.parseRequest(request);
				System.out.println(fileItemList);
				int Size = fileItemList.size();
				System.out.println("리스트 사이즈" + Size);
				for (int i = 0; i < Size; i++) {
					System.out.println("=========4========");
					FileItem fileItem = (FileItem) fileItemList.get(i);
					System.out.println(fileItem + "번쨰" + i);
					System.out.println(fileItem.isFormField());
					if (fileItem.isFormField()) {
						if (fileItem.getFieldName().equals("manuDate")) {
							token = new StringTokenizer(fileItem.getString("euc-kr"), "-");
							String manuDate = token.nextToken() + token.nextToken() + token.nextToken();
							product.setManuDate(manuDate);
						} else if (fileItem.getFieldName().equals("prodName"))
							product.setProdName(fileItem.getString("euc-kr"));

						else if (fileItem.getFieldName().equals("prodDetail"))
							product.setProdDetail(fileItem.getString("euc-kr"));

						else if (fileItem.getFieldName().equals("price"))
							product.setPrice(Integer.parseInt(fileItem.getString("euc-kr")));

					} else {
						System.out.println("=========5========");
						
						if (fileItem.getSize() > 0) {
							System.out.println(fileItem.getName());
							int idx = fileItem.getName().lastIndexOf("\\");
							if (idx == -1) {
								idx = fileItem.getName().lastIndexOf("/");
							}
							String fileName = fileItem.getName().substring(idx + 1);
							product.setFileName(fileName);
							System.out.println(product);
							try {
								File uploadedFile = new File(temDir2, fileName);
								fileItem.write(uploadedFile);

							} catch (IOException e) {
								System.out.println(e);
							}

						} else {
							product.setFileName("../../images/empty.GIF");
							
						}
					}
				}
				ProductDaoImpl service = new ProductDaoImpl();
				service.addProduct(product);
				request.setAttribute("product", product);
			} else {
				int overSize = (request.getContentLength() / 1000000);
				System.out.println("<script>alert(파일의 크기는 1MB까지 입니다. 올리신파일 용량은" + overSize + "MB 입니다.);");
				System.out.println("history.back();</scirpt>");
			}

		} else {

			System.out.println("인코딩 타입이 multipart/form-data가 아닙니다..");
		}

		return "forward:/product/addProduct.jsp";
	}

}
