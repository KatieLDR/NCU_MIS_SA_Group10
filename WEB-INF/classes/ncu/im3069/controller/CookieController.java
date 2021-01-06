package ncu.im3069.controller;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.Cookie;

import org.json.*;

import ncu.im3069.app.MemberMusicHelper;
import ncu.im3069.app.Order;
import ncu.im3069.app.OrderHelper;
import ncu.im3069.app.Product;
import ncu.im3069.app.ProductHelper;
import ncu.im3069.tools.JsonReader;

import javax.servlet.annotation.WebServlet;

@WebServlet("/api/cookie.do")
public class CookieController extends HttpServlet {

    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	private MemberMusicHelper mmh =  MemberMusicHelper.getHelper();
	
    public CookieController() {
        super();
    }

    /**
     * 處理 Http Method 請求 GET 方法（新增資料）
     *
     * @param request Servlet 請求之 HttpServletRequest 之 Request 物件（前端到後端）
     * @param response Servlet 回傳之 HttpServletResponse 之 Response 物件（後端到前端）
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		HttpSession session =request.getSession();
		Cookie cookies[] = request.getCookies();
		
		String str=null;
		
		for(Cookie c : cookies) {
			if(c.getName().equals("user_id")){
				response.addCookie(c);
				str=c.getValue();
			}
		}
		
		//int mem_id = Integer.parseInt(str);
		
		/** 透過 JsonReader 類別將 Request 之 JSON 格式資料解析並取回 */
        JsonReader jsr = new JsonReader(request);
        
        JSONObject query = mmh.getByID(str);
        
        /** 新建一個 JSONObject 用於將回傳之資料進行封裝 */
        JSONObject resp = new JSONObject();
        resp.put("status", "200");
        resp.put("message", "Cookie取得成功");
        resp.put("response", query);

        /** 透過 JsonReader 物件回傳到前端（以 JSONObject 方式） */
        jsr.response(resp, response);
	}

    /**
     * 處理 Http Method 請求 POST 方法（新增資料）
     *
     * @param request Servlet 請求之 HttpServletRequest 之 Request 物件（前端到後端）
     * @param response Servlet 回傳之 HttpServletResponse 之 Response 物件（後端到前端）
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	    /** 透過 JsonReader 類別將 Request 之 JSON 格式資料解析並取回 */
//        JsonReader jsr = new JsonReader(request);
//        JSONObject jso = jsr.getObject();
//
//        /** 取出經解析到 JSONObject 之 Request 參數 */
//        String first_name = jso.getString("first_name");
//        String last_name = jso.getString("last_name");
//        String email = jso.getString("email");
//        String address = jso.getString("address");
//        String phone = jso.getString("phone");
//        JSONArray item = jso.getJSONArray("item");
//        JSONArray quantity = jso.getJSONArray("quantity");
//
//        /** 建立一個新的訂單物件 */
//        Order od = new Order(first_name, last_name, email, address, phone);
//
//        /** 將每一筆訂單細項取得出來 */
//        for(int i=0 ; i < item.length() ; i++) {
//            String product_id = item.getString(i);
//            int amount = quantity.getInt(i);
//
//            /** 透過 ProductHelper 物件之 getById()，取得產品的資料並加進訂單物件裡 */
//            Product pd = ph.getById(product_id);
//            od.addOrderProduct(pd, amount);
//        }
//
//        /** 透過 orderHelper 物件的 create() 方法新建一筆訂單至資料庫 */
//        JSONObject   = oh.create(od);
//
//        /** 設定回傳回來的訂單編號與訂單細項編號 */
//        od.setId((int) result.getLong("order_id"));
//        od.setOrderProductId(result.getJSONArray("order_product_id"));
//
//        /** 新建一個 JSONObject 用於將回傳之資料進行封裝 */
//        JSONObject resp = new JSONObject();
//        resp.put("status", "200");
//        resp.put("message", "訂單新增成功！");
//        resp.put("response", od.getOrderAllInfo());
//
//        /** 透過 JsonReader 物件回傳到前端（以 JSONObject 方式） */
//        jsr.response(resp, response);
//	}

}
