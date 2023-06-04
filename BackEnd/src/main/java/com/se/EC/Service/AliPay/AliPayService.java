package com.se.EC.Service.AliPay;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.util.ResponseChecker;
import com.alipay.easysdk.payment.common.models.AlipayTradeQueryResponse;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import com.se.EC.Utils.JSONUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
public class AliPayService {

    /**
     * 生成支付表单
     * @param subject
     * @param money
     * @return
     * @throws Exception
     */
    public String toPay(String subject, BigDecimal money) throws Exception {
        // 最后一个参数是支付完成之后跳转到的界面, 一般为项目的首页
        AlipayTradePagePayResponse pay = Factory.Payment.Page().pay(subject, this.generateTradeNo(),
                String.valueOf(money), "http://localhost:8080");
        String payForm = null;
        if (ResponseChecker.success(pay)) {
            payForm = pay.getBody();
        }
        return payForm;
    }

//    public String transfer() throws Exception{
//
//        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig);
//        AlipayFundTransUniTransferRequest request = new AlipayFundTransUniTransferRequest();
//        AlipayFundTransUniTransferModel model = new AlipayFundTransUniTransferModel();
//        model.setOutBizNo("201806300001");
//        model.setRemark("201905代发");
//        model.setBusinessParams("{\"payer_show_name_use_alias\":\"true\"}");
//        model.setBizScene("DIRECT_TRANSFER");
//        Participant payeeInfo = new Participant();
//        payeeInfo.setIdentity("2088123412341234");
//        payeeInfo.setIdentityType("ALIPAY_USER_ID");
//        payeeInfo.setName("黄龙国际有限公司");
//        model.setPayeeInfo(payeeInfo);
//        model.setTransAmount("23.00");
//        model.setProductCode("TRANS_ACCOUNT_NO_PWD");
//        model.setOrderTitle("201905代发");
//        request.setBizModel(model);
//        AlipayFundTransUniTransferResponse response = alipayClient.certificateExecute(request);
//        System.out.println(response.getBody());
//        if (response.isSuccess()) {
//            System.out.println("调用成功");
//        } else {
//            System.out.println("调用失败");
//        }
//
//
//    }
    /**
     * 通过时间生成外部订单号 out_trade_no
     * @return
     */
    private String generateTradeNo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String tradeNo = LocalDateTime.now(ZoneOffset.of("+8")).format(formatter);
        return tradeNo;
    }


    public Object queryTradeStatus(String outTradeNo) throws Exception {
        AlipayTradeQueryResponse query = Factory.Payment.Common().query(outTradeNo);
        Map<String, Object> map = JSONUtils.jsonToMap(query.getHttpBody());

        // 返回交易结果, 是否交易成功需要根据该对象中的 trade_status 来确定
        // trade_status 的枚举值如下, 请见 https://opendocs.alipay.com/apis/api_1/alipay.trade.query
        // WAIT_BUYER_PAY（交易创建，等待买家付款）
        // TRADE_CLOSED（未付款交易超时关闭，或支付完成后全额退款）
        // TRADE_SUCCESS（交易支付成功）
        // TRADE_FINISHED（交易结束，不可退款）
        // 当 trade_status 等于 TRADE_SUCCESS 或 TRADE_FINISHED 时, 表示支付成功
        return map.get("alipay_trade_query_response");
    }

}

