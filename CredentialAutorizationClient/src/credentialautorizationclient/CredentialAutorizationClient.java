/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package credentialautorizationclient;

import com.alodiga.autorization.credential.response.BalanceInquiryWithMovementsResponse;
import com.alodiga.autorization.credential.response.BalanceInquiryWithoutMovementsResponse;
import com.alodiga.autorization.credential.response.DispertionResponse;
import com.alodiga.autorization.credential.response.LimitAdvanceResponse;
import com.alodiga.autorization.credential.response.Movimiento;
import com.alodiga.autorization.credential.response.Movimientos;
import com.alodiga.autorization.utils.Constants;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author ltoro
 */
public class CredentialAutorizationClient {

     private static final int CONNECTION_TIMEOUT = 5000;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MalformedURLException, IOException, ConnectException, Exception {
        // TODO code application logic here
        //DispertionResponse dispertionResponse = new CredentialAutorizationClient().dispertionTransfer("20201029", "090950", "5496720000774011", "10");
        //LimitAdvanceResponse limitAdvanceResponse = new CredentialAutorizationClient().limitAdvance("20201029", "090950", "5496720000774011", "10","");
        CredentialAutorizationClient autorizationClient = new CredentialAutorizationClient();
        BalanceInquiryWithoutMovementsResponse balanceInquiryWithoutMovementsResponse = new BalanceInquiryWithoutMovementsResponse();
        
        //balanceInquiryWithoutMovementsResponse = autorizationClient.balanceInquiryWithoutMovements("20201113", "120950", "52558525874460001", "202011041009501234");
        //System.out.println("consumos " + balanceInquiryWithoutMovementsResponse.getDisponibleConsumos());
        //autorizationClient.dispertionTransfer("20201113", "100950", "52558525874460001", "10", "202011041009501233");
        //autorizationClient.dispertionReverseTransfer("20201113", "100950", "202011041009501233", "52558525874460001", "1");
        //autorizationClient.limitAdvance("20201113", "120950", "52558525874460001", "10", "202011041009501233");
        autorizationClient.limitAdvanceReverse("20201113", "120950", "202011041009501233", "52558525874460001", "1");
    }
    
    
    public DispertionResponse dispertionTransfer(String date, String hour, String numberCard, String balance, String sequence) throws SocketTimeoutException, ConnectException, MalformedURLException, IOException, Exception {
        String formattedSOAPResponse = "";

        try {

            ignoreSSL();
            String responseString = "";
            String outputString = "";
            String wsEndPoint = Constants.URL_TEST;
            URL url = new URL(wsEndPoint);
            URLConnection connection = url.openConnection();
            HttpURLConnection httpConn = (HttpURLConnection) connection;
            httpConn.setConnectTimeout(CONNECTION_TIMEOUT);
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            StringBuilder builder = new StringBuilder("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://org.apache.synapse/xsd\">");
            builder.append("<soapenv:Header/>");
            builder.append("<soapenv:Body>");
            builder.append("<xsd:Autorizar>");
            builder.append("<xsd:Canal>" + Constants.CANAL + "</xsd:Canal>");
            builder.append("<xsd:TipoTransaccion>" + Constants.NUMBER_TRANSACTION_TYPE_DISPERTION + "</xsd:TipoTransaccion>");
            builder.append("<xsd:FechaTransaccion>" + date + "</xsd:FechaTransaccion>");
            builder.append("<xsd:HoraTransaccion>" + hour + "</xsd:HoraTransaccion>");
            builder.append("<xsd:SecuenciaTransaccion>" + sequence + "</xsd:SecuenciaTransaccion>");
            builder.append("<xsd:Terminal>" + Constants.IP_NUMBER_TEST + "</xsd:Terminal>");
            builder.append("<xsd:ModoIngreso>" + Constants.NUMBER_ENTRY_MODE + "</xsd:ModoIngreso>");
            builder.append("<xsd:Tarjeta>" + numberCard + "</xsd:Tarjeta>");
            builder.append("<xsd:CodigoMoneda>" + Constants.CURRENCY_CODE + "</xsd:CodigoMoneda>");
            builder.append("<xsd:Importe>" + balance + "</xsd:Importe>");
            builder.append("<xsd:NombreComercio>" + Constants.CONCEPT_TRANSFER_DISPERTION + "</xsd:NombreComercio>");
            builder.append("<xsd:SubTipoTransaccion>" + Constants.NUMBER_SUB_TRANSACTION_TYPE_DISPERTION + "</xsd:SubTipoTransaccion>");
            builder.append("</xsd:Autorizar>");
            builder.append("</soapenv:Body>");
            builder.append("</soapenv:Envelope>");
            byte[] buffer = new byte[builder.toString().length()];
            buffer = builder.toString().getBytes();
            bout.write(buffer);
            byte[] b = bout.toByteArray();
            String SOAPAction = "Consultar";
            httpConn.setRequestProperty("Content-Length", String.valueOf(b.length));
            httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            httpConn.setRequestProperty("SOAPAction", SOAPAction);
            httpConn.setRequestMethod("POST");
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            OutputStream out = httpConn.getOutputStream();
            // Write the content of the request to the outputstream of the HTTP
            // Connection.
            out.write(b);
            out.close();
            // Ready with sending the request.
            // Read the response.
            InputStreamReader isr = new InputStreamReader(httpConn.getInputStream(), Charset.forName("UTF-8"));
            BufferedReader in = new BufferedReader(isr);
            // Write the SOAP message response to a String.

            while ((responseString = in.readLine()) != null) {
                outputString = outputString + responseString;
            }
            formattedSOAPResponse = formatXML(outputString);
            System.out.println(outputString);
            DispertionResponse dispertionResponse = new DispertionResponse();
            try {
                dispertionResponse.setCodigoError(getTagValue("ns:CodigoError", formattedSOAPResponse));
                dispertionResponse.setMensajeError(getTagValue("ns:MensajeError", formattedSOAPResponse));
                dispertionResponse.setCodigoRespuesta(getTagValue("ns:CodigoRespuesta", formattedSOAPResponse));
                dispertionResponse.setMensajeRespuesta(getTagValue("ns:MensajeRespuesta", formattedSOAPResponse));
                dispertionResponse.setCodigoAutorizacion(getTagValue("ns:CodigoAutorizacion", formattedSOAPResponse));
            } catch (ArrayIndexOutOfBoundsException e) {
                dispertionResponse.setCodigoError(getTagValue("ns:CodigoError", formattedSOAPResponse));
                dispertionResponse.setMensajeError(getTagValue("ns:MensajeError", formattedSOAPResponse));
                dispertionResponse.setCodigoRespuesta(getTagValue("ns:CodigoRespuesta", formattedSOAPResponse));
                dispertionResponse.setMensajeRespuesta(getTagValue("ns:MensajeRespuesta", formattedSOAPResponse));

            }
            return new DispertionResponse(dispertionResponse.getCodigoError(), dispertionResponse.getMensajeError(), dispertionResponse.getCodigoRespuesta(), dispertionResponse.getMensajeRespuesta(), dispertionResponse.getCodigoAutorizacion());

        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            throw new SocketTimeoutException();
        } catch (ConnectException ex) {
            ex.printStackTrace();
            throw new ConnectException();
        }catch (MalformedURLException ex) {
            ex.printStackTrace();
            throw new MalformedURLException();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new IOException();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception();
        }
    }
    
    
    public LimitAdvanceResponse limitAdvance(String date, String hour, String numberCard, String balance, String sequence) throws SocketTimeoutException, ConnectException, MalformedURLException, IOException, Exception {
        String formattedSOAPResponse = "";

        try {

            ignoreSSL();
            String responseString = "";
            String outputString = "";
            String wsEndPoint = Constants.URL_TEST;
            URL url = new URL(wsEndPoint);
            URLConnection connection = url.openConnection();
            HttpURLConnection httpConn = (HttpURLConnection) connection;
            httpConn.setConnectTimeout(CONNECTION_TIMEOUT);
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            StringBuilder builder = new StringBuilder("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://org.apache.synapse/xsd\">");
            builder.append("<soapenv:Header/>");
            builder.append("<soapenv:Body>");
            builder.append("<xsd:Autorizar>");
            builder.append("<xsd:Canal>" + Constants.CANAL + "</xsd:Canal>");
            builder.append("<xsd:TipoTransaccion>" + Constants.NUMBER_TRANSACTION_TYPE_LIMIT_ADVANCE + "</xsd:TipoTransaccion>");
            builder.append("<xsd:FechaTransaccion>" + date + "</xsd:FechaTransaccion>");
            builder.append("<xsd:HoraTransaccion>" + hour + "</xsd:HoraTransaccion>");
            builder.append("<xsd:SecuenciaTransaccion>" + sequence + "</xsd:SecuenciaTransaccion>");
            builder.append("<xsd:Terminal>" + Constants.IP_NUMBER_TEST + "</xsd:Terminal>");
            builder.append("<xsd:ModoIngreso>" + Constants.NUMBER_ENTRY_MODE + "</xsd:ModoIngreso>");
            builder.append("<xsd:Tarjeta>" + numberCard + "</xsd:Tarjeta>");
            builder.append("<xsd:CodigoMoneda>" + Constants.CURRENCY_CODE + "</xsd:CodigoMoneda>");
            builder.append("<xsd:Importe>" + balance + "</xsd:Importe>");
            builder.append("<xsd:NombreComercio>" + Constants.CONCEPT_TRANSFER_LIMIT_ADVANCE + "</xsd:NombreComercio>");
            builder.append("<xsd:SubTipoTransaccion>" + Constants.NUMBER_SUB_TRANSACTION_TYPE_LIMIT_ADVANCE + "</xsd:SubTipoTransaccion>");
            builder.append("</xsd:Autorizar>");
            builder.append("</soapenv:Body>");
            builder.append("</soapenv:Envelope>");
            byte[] buffer = new byte[builder.toString().length()];
            buffer = builder.toString().getBytes();
            bout.write(buffer);
            byte[] b = bout.toByteArray();
            String SOAPAction = "Consultar";
            httpConn.setRequestProperty("Content-Length", String.valueOf(b.length));
            httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            httpConn.setRequestProperty("SOAPAction", SOAPAction);
            httpConn.setRequestMethod("POST");
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            OutputStream out = httpConn.getOutputStream();
            // Write the content of the request to the outputstream of the HTTP
            // Connection.
            out.write(b);
            out.close();
            // Ready with sending the request.
            // Read the response.
            InputStreamReader isr = new InputStreamReader(httpConn.getInputStream(), Charset.forName("UTF-8"));
            BufferedReader in = new BufferedReader(isr);
            // Write the SOAP message response to a String.

            while ((responseString = in.readLine()) != null) {
                outputString = outputString + responseString;
            }
            formattedSOAPResponse = formatXML(outputString);
            System.out.println(outputString);
            LimitAdvanceResponse limitAdvanceResponse = new LimitAdvanceResponse();
            try {
                limitAdvanceResponse.setCodigoError(getTagValue("ns:CodigoError", formattedSOAPResponse));
                limitAdvanceResponse.setMensajeError(getTagValue("ns:MensajeError", formattedSOAPResponse));
                limitAdvanceResponse.setCodigoRespuesta(getTagValue("ns:CodigoRespuesta", formattedSOAPResponse));
                limitAdvanceResponse.setMensajeRespuesta(getTagValue("ns:MensajeRespuesta", formattedSOAPResponse));
                limitAdvanceResponse.setCodigoAutorizacion(getTagValue("ns:CodigoAutorizacion", formattedSOAPResponse));
            } catch (ArrayIndexOutOfBoundsException e) {
                limitAdvanceResponse.setCodigoError(getTagValue("ns:CodigoError", formattedSOAPResponse));
                limitAdvanceResponse.setMensajeError(getTagValue("ns:MensajeError", formattedSOAPResponse));
                limitAdvanceResponse.setCodigoRespuesta(getTagValue("ns:CodigoRespuesta", formattedSOAPResponse));
                limitAdvanceResponse.setMensajeRespuesta(getTagValue("ns:MensajeRespuesta", formattedSOAPResponse));

            }
            return new LimitAdvanceResponse(limitAdvanceResponse.getCodigoError(), limitAdvanceResponse.getMensajeError(), limitAdvanceResponse.getCodigoRespuesta(), limitAdvanceResponse.getMensajeRespuesta(), limitAdvanceResponse.getCodigoAutorizacion());

        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            throw new SocketTimeoutException();
        } catch (ConnectException ex) {
            ex.printStackTrace();
            throw new ConnectException();
        }catch (MalformedURLException ex) {
            ex.printStackTrace();
            throw new MalformedURLException();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new IOException();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception();
        }
    }
    
    public BalanceInquiryWithoutMovementsResponse balanceInquiryWithoutMovements(String date, String hour, String numberCard, String sequence) throws SocketTimeoutException, ConnectException, MalformedURLException, IOException, Exception {
        String formattedSOAPResponse = "";

        try {

            ignoreSSL();
            String responseString = "";
            String outputString = "";
            String wsEndPoint = Constants.URL_TEST;
            URL url = new URL(wsEndPoint);
            URLConnection connection = url.openConnection();
            HttpURLConnection httpConn = (HttpURLConnection) connection;
            httpConn.setConnectTimeout(CONNECTION_TIMEOUT);
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            StringBuilder builder = new StringBuilder("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://org.apache.synapse/xsd\">");
            builder.append("<soapenv:Header/>");
            builder.append("<soapenv:Body>");
            builder.append("<xsd:Autorizar>");
            builder.append("<xsd:Canal>" + Constants.CANAL + "</xsd:Canal>");
            builder.append("<xsd:TipoTransaccion>" + Constants.NUMBER_TRANSACTION_TYPE_BALANCE_INQUIRY + "</xsd:TipoTransaccion>");
            builder.append("<xsd:FechaTransaccion>" + date + "</xsd:FechaTransaccion>");
            builder.append("<xsd:HoraTransaccion>" + hour + "</xsd:HoraTransaccion>");
            builder.append("<xsd:SecuenciaTransaccion>" + sequence + "</xsd:SecuenciaTransaccion>");
            builder.append("<xsd:Terminal>" + Constants.IP_NUMBER_TEST + "</xsd:Terminal>");
            builder.append("<xsd:Tarjeta>" + numberCard + "</xsd:Tarjeta>");
            builder.append("<xsd:SubTipoTransaccion>" + Constants.NUMBER_SUB_TRANSACTION_BALANCE_INQUIRY_WITHOUT_MOVEMENTS + "</xsd:SubTipoTransaccion>");
            builder.append("</xsd:Autorizar>");
            builder.append("</soapenv:Body>");
            builder.append("</soapenv:Envelope>");
            byte[] buffer = new byte[builder.toString().length()];
            buffer = builder.toString().getBytes();
            bout.write(buffer);
            byte[] b = bout.toByteArray();
            String SOAPAction = "Consultar";
            httpConn.setRequestProperty("Content-Length", String.valueOf(b.length));
            httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            httpConn.setRequestProperty("SOAPAction", SOAPAction);
            httpConn.setRequestMethod("POST");
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            OutputStream out = httpConn.getOutputStream();
            // Write the content of the request to the outputstream of the HTTP
            // Connection.
            out.write(b);
            out.close();
            // Ready with sending the request.
            // Read the response.
            InputStreamReader isr = new InputStreamReader(httpConn.getInputStream(), Charset.forName("UTF-8"));
            BufferedReader in = new BufferedReader(isr);
            // Write the SOAP message response to a String.

            while ((responseString = in.readLine()) != null) {
                outputString = outputString + responseString;
            }
            formattedSOAPResponse = formatXML(outputString);
            System.out.println(outputString);
            BalanceInquiryWithoutMovementsResponse balanceInquiryWithoutMovementsResponse = new BalanceInquiryWithoutMovementsResponse();
            try {
                balanceInquiryWithoutMovementsResponse.setCodigoError(getTagValue("ns:CodigoError", formattedSOAPResponse));
                balanceInquiryWithoutMovementsResponse.setMensajeError(getTagValue("ns:MensajeError", formattedSOAPResponse));
                balanceInquiryWithoutMovementsResponse.setCodigoRespuesta(getTagValue("ns:CodigoRespuesta", formattedSOAPResponse));
                balanceInquiryWithoutMovementsResponse.setMensajeRespuesta(getTagValue("ns:MensajeRespuesta", formattedSOAPResponse));
                balanceInquiryWithoutMovementsResponse.setCodigoAutorizacion(getTagValue("ns:CodigoAutorizacion", formattedSOAPResponse));
                balanceInquiryWithoutMovementsResponse.setDisponibleConsumos(getTagValue("ns:DisponibleConsumos", formattedSOAPResponse));
                balanceInquiryWithoutMovementsResponse.setDisponibleCuotas(getTagValue("ns:DisponibleCuotas", formattedSOAPResponse));
                balanceInquiryWithoutMovementsResponse.setDisponibleAdelantos(getTagValue("ns:DisponibleAdelantos", formattedSOAPResponse));
                balanceInquiryWithoutMovementsResponse.setDisponiblePrestamos(getTagValue("ns:DisponiblePrestamos", formattedSOAPResponse));
                balanceInquiryWithoutMovementsResponse.setSaldo(getTagValue("ns:Saldo", formattedSOAPResponse));
                balanceInquiryWithoutMovementsResponse.setSaldoEnDolares(getTagValue("ns:SaldoEnDolares", formattedSOAPResponse));
                balanceInquiryWithoutMovementsResponse.setPagoMinimo(getTagValue("ns:PagoMinimo", formattedSOAPResponse));            
            } catch (ArrayIndexOutOfBoundsException e) {
                balanceInquiryWithoutMovementsResponse.setCodigoError(getTagValue("ns:CodigoError", formattedSOAPResponse));
                balanceInquiryWithoutMovementsResponse.setMensajeError(getTagValue("ns:MensajeError", formattedSOAPResponse));
                balanceInquiryWithoutMovementsResponse.setCodigoRespuesta(getTagValue("ns:CodigoRespuesta", formattedSOAPResponse));
                balanceInquiryWithoutMovementsResponse.setMensajeRespuesta(getTagValue("ns:MensajeRespuesta", formattedSOAPResponse));

            }
            return new BalanceInquiryWithoutMovementsResponse(balanceInquiryWithoutMovementsResponse.getCodigoError(), balanceInquiryWithoutMovementsResponse.getMensajeError(), balanceInquiryWithoutMovementsResponse.getCodigoRespuesta(), balanceInquiryWithoutMovementsResponse.getMensajeRespuesta(), balanceInquiryWithoutMovementsResponse.getCodigoAutorizacion(),balanceInquiryWithoutMovementsResponse.getDisponibleConsumos(),balanceInquiryWithoutMovementsResponse.getDisponibleCuotas(),balanceInquiryWithoutMovementsResponse.getDisponibleAdelantos(),balanceInquiryWithoutMovementsResponse.getDisponiblePrestamos(),balanceInquiryWithoutMovementsResponse.getSaldo(), balanceInquiryWithoutMovementsResponse.getSaldoEnDolares(),balanceInquiryWithoutMovementsResponse.getPagoMinimo());

        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            throw new SocketTimeoutException();
        } catch (ConnectException ex) {
            ex.printStackTrace();
            throw new ConnectException();
        }catch (MalformedURLException ex) {
            ex.printStackTrace();
            throw new MalformedURLException();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new IOException();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception();
        }
    }
    
    public DispertionResponse dispertionReverseTransfer(String date, String hour, String sequence,String numberCard, String balance) throws SocketTimeoutException, ConnectException, MalformedURLException, IOException, Exception {
        String formattedSOAPResponse = "";

        try {

            ignoreSSL();
            String responseString = "";
            String outputString = "";
            String wsEndPoint = Constants.URL_TEST;
            URL url = new URL(wsEndPoint);
            URLConnection connection = url.openConnection();
            HttpURLConnection httpConn = (HttpURLConnection) connection;
            httpConn.setConnectTimeout(CONNECTION_TIMEOUT);
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            StringBuilder builder = new StringBuilder("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://org.apache.synapse/xsd\">");
            builder.append("<soapenv:Header/>");
            builder.append("<soapenv:Body>");
            builder.append("<xsd:Autorizar>");
            builder.append("<xsd:Canal>" + Constants.CANAL + "</xsd:Canal>");
            builder.append("<xsd:TipoTransaccion>" + Constants.NUMBER_TRANSACTION_TYPE_DISPERTION_REVERSE + "</xsd:TipoTransaccion>");
            builder.append("<xsd:SubTipoTransaccion>" + Constants.NUMBER_SUB_TRANSACTION_TYPE_DISPERTION_REVERSE + "</xsd:SubTipoTransaccion>");
            builder.append("<xsd:FechaTransaccion>" + date + "</xsd:FechaTransaccion>");
            builder.append("<xsd:HoraTransaccion>" + hour + "</xsd:HoraTransaccion>");
            builder.append("<xsd:SecuenciaTransaccion>" + sequence + "</xsd:SecuenciaTransaccion>");
            builder.append("<xsd:Terminal>" + Constants.IP_NUMBER_TEST + "</xsd:Terminal>");
            builder.append("<xsd:ModoIngreso>" + Constants.NUMBER_ENTRY_MODE + "</xsd:ModoIngreso>");
            builder.append("<xsd:Tarjeta>" + numberCard + "</xsd:Tarjeta>");
            builder.append("<xsd:CodigoMoneda>" + Constants.CURRENCY_CODE + "</xsd:CodigoMoneda>");
            builder.append("<xsd:Importe>" + balance + "</xsd:Importe>");
            builder.append("</xsd:Autorizar>");
            builder.append("</soapenv:Body>");
            builder.append("</soapenv:Envelope>");
            byte[] buffer = new byte[builder.toString().length()];
            buffer = builder.toString().getBytes();
            bout.write(buffer);
            byte[] b = bout.toByteArray();
            String SOAPAction = "Consultar";
            httpConn.setRequestProperty("Content-Length", String.valueOf(b.length));
            httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            httpConn.setRequestProperty("SOAPAction", SOAPAction);
            httpConn.setRequestMethod("POST");
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            OutputStream out = httpConn.getOutputStream();
            // Write the content of the request to the outputstream of the HTTP
            // Connection.
            out.write(b);
            out.close();
            // Ready with sending the request.
            // Read the response.
            InputStreamReader isr = new InputStreamReader(httpConn.getInputStream(), Charset.forName("UTF-8"));
            BufferedReader in = new BufferedReader(isr);
            // Write the SOAP message response to a String.

            while ((responseString = in.readLine()) != null) {
                outputString = outputString + responseString;
            }
            formattedSOAPResponse = formatXML(outputString);
            System.out.println(outputString);
            DispertionResponse dispertionResponse = new DispertionResponse();
            try {
                dispertionResponse.setCodigoError(getTagValue("ns:CodigoError", formattedSOAPResponse));
                dispertionResponse.setMensajeError(getTagValue("ns:MensajeError", formattedSOAPResponse));
                dispertionResponse.setCodigoRespuesta(getTagValue("ns:CodigoRespuesta", formattedSOAPResponse));
                dispertionResponse.setMensajeRespuesta(getTagValue("ns:MensajeRespuesta", formattedSOAPResponse));
                dispertionResponse.setCodigoAutorizacion(getTagValue("ns:CodigoAutorizacion", formattedSOAPResponse));
            } catch (ArrayIndexOutOfBoundsException e) {
                dispertionResponse.setCodigoError(getTagValue("ns:CodigoError", formattedSOAPResponse));
                dispertionResponse.setMensajeError(getTagValue("ns:MensajeError", formattedSOAPResponse));
                dispertionResponse.setCodigoRespuesta(getTagValue("ns:CodigoRespuesta", formattedSOAPResponse));
                dispertionResponse.setMensajeRespuesta(getTagValue("ns:MensajeRespuesta", formattedSOAPResponse));

            }
            return new DispertionResponse(dispertionResponse.getCodigoError(), dispertionResponse.getMensajeError(), dispertionResponse.getCodigoRespuesta(), dispertionResponse.getMensajeRespuesta(), dispertionResponse.getCodigoAutorizacion());

        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            throw new SocketTimeoutException();
        } catch (ConnectException ex) {
            ex.printStackTrace();
            throw new ConnectException();
        }catch (MalformedURLException ex) {
            ex.printStackTrace();
            throw new MalformedURLException();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new IOException();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception();
        }
    }
    
    public LimitAdvanceResponse limitAdvanceReverse(String date, String hour, String sequence,String numberCard, String balance) throws SocketTimeoutException, ConnectException, MalformedURLException, IOException, Exception {
        String formattedSOAPResponse = "";

        try {

            ignoreSSL();
            String responseString = "";
            String outputString = "";
            String wsEndPoint = Constants.URL_TEST;
            URL url = new URL(wsEndPoint);
            URLConnection connection = url.openConnection();
            HttpURLConnection httpConn = (HttpURLConnection) connection;
            httpConn.setConnectTimeout(CONNECTION_TIMEOUT);
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            StringBuilder builder = new StringBuilder("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://org.apache.synapse/xsd\">");
            builder.append("<soapenv:Header/>");
            builder.append("<soapenv:Body>");
            builder.append("<xsd:Autorizar>");
            builder.append("<xsd:Canal>" + Constants.CANAL + "</xsd:Canal>");
            builder.append("<xsd:TipoTransaccion>" + Constants.NUMBER_TRANSACTION_TYPE_LIMIT_ADVANCE_REVERSE + "</xsd:TipoTransaccion>");
            builder.append("<xsd:SubTipoTransaccion>" + Constants.NUMBER_SUB_TRANSACTION_TYPE_LIMIT_ADVANCE_REVERSE + "</xsd:SubTipoTransaccion>");
            builder.append("<xsd:FechaTransaccion>" + date + "</xsd:FechaTransaccion>");
            builder.append("<xsd:HoraTransaccion>" + hour + "</xsd:HoraTransaccion>");
            builder.append("<xsd:SecuenciaTransaccion>" + sequence + "</xsd:SecuenciaTransaccion>");
            builder.append("<xsd:Terminal>" + Constants.IP_NUMBER_TEST + "</xsd:Terminal>");
            builder.append("<xsd:ModoIngreso>" + Constants.NUMBER_ENTRY_MODE + "</xsd:ModoIngreso>");
            builder.append("<xsd:Tarjeta>" + numberCard + "</xsd:Tarjeta>");
            builder.append("<xsd:CodigoMoneda>" + Constants.CURRENCY_CODE + "</xsd:CodigoMoneda>");
            builder.append("<xsd:Importe>" + balance + "</xsd:Importe>");
            builder.append("</xsd:Autorizar>");
            builder.append("</soapenv:Body>");
            builder.append("</soapenv:Envelope>");
            byte[] buffer = new byte[builder.toString().length()];
            buffer = builder.toString().getBytes();
            bout.write(buffer);
            byte[] b = bout.toByteArray();
            String SOAPAction = "Consultar";
            httpConn.setRequestProperty("Content-Length", String.valueOf(b.length));
            httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            httpConn.setRequestProperty("SOAPAction", SOAPAction);
            httpConn.setRequestMethod("POST");
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            OutputStream out = httpConn.getOutputStream();
            // Write the content of the request to the outputstream of the HTTP
            // Connection.
            out.write(b);
            out.close();
            // Ready with sending the request.
            // Read the response.
            InputStreamReader isr = new InputStreamReader(httpConn.getInputStream(), Charset.forName("UTF-8"));
            BufferedReader in = new BufferedReader(isr);
            // Write the SOAP message response to a String.

            while ((responseString = in.readLine()) != null) {
                outputString = outputString + responseString;
            }
            formattedSOAPResponse = formatXML(outputString);
            System.out.println(outputString);
            LimitAdvanceResponse limitAdvanceResponse = new LimitAdvanceResponse();
            try {
                limitAdvanceResponse.setCodigoError(getTagValue("ns:CodigoError", formattedSOAPResponse));
                limitAdvanceResponse.setMensajeError(getTagValue("ns:MensajeError", formattedSOAPResponse));
                limitAdvanceResponse.setCodigoRespuesta(getTagValue("ns:CodigoRespuesta", formattedSOAPResponse));
                limitAdvanceResponse.setMensajeRespuesta(getTagValue("ns:MensajeRespuesta", formattedSOAPResponse));
                limitAdvanceResponse.setCodigoAutorizacion(getTagValue("ns:CodigoAutorizacion", formattedSOAPResponse));
            } catch (ArrayIndexOutOfBoundsException e) {
                limitAdvanceResponse.setCodigoError(getTagValue("ns:CodigoError", formattedSOAPResponse));
                limitAdvanceResponse.setMensajeError(getTagValue("ns:MensajeError", formattedSOAPResponse));
                limitAdvanceResponse.setCodigoRespuesta(getTagValue("ns:CodigoRespuesta", formattedSOAPResponse));
                limitAdvanceResponse.setMensajeRespuesta(getTagValue("ns:MensajeRespuesta", formattedSOAPResponse));

            }
            return new LimitAdvanceResponse(limitAdvanceResponse.getCodigoError(), limitAdvanceResponse.getMensajeError(), limitAdvanceResponse.getCodigoRespuesta(), limitAdvanceResponse.getMensajeRespuesta(), limitAdvanceResponse.getCodigoAutorizacion());

        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            throw new SocketTimeoutException();
        } catch (ConnectException ex) {
            ex.printStackTrace();
            throw new ConnectException();
        }catch (MalformedURLException ex) {
            ex.printStackTrace();
            throw new MalformedURLException();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new IOException();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception();
        }
    }
    
    public BalanceInquiryWithMovementsResponse balanceInquiryWithMovements(String date, String hour, String numberCard, String sequence) throws SocketTimeoutException, ConnectException, MalformedURLException, IOException, Exception {
        String formattedSOAPResponse = "";

        try {

            ignoreSSL();
            String responseString = "";
            String outputString = "";
            String wsEndPoint = Constants.URL_TEST;
            URL url = new URL(wsEndPoint);
            URLConnection connection = url.openConnection();
            HttpURLConnection httpConn = (HttpURLConnection) connection;
            httpConn.setConnectTimeout(CONNECTION_TIMEOUT);
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            StringBuilder builder = new StringBuilder("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://org.apache.synapse/xsd\">");
            builder.append("<soapenv:Header/>");
            builder.append("<soapenv:Body>");
            builder.append("<xsd:Autorizar>");
            builder.append("<xsd:Canal>" + Constants.CANAL + "</xsd:Canal>");
            builder.append("<xsd:TipoTransaccion>" + Constants.NUMBER_TRANSACTION_TYPE_BALANCE_INQUIRY + "</xsd:TipoTransaccion>");
            builder.append("<xsd:FechaTransaccion>" + date + "</xsd:FechaTransaccion>");
            builder.append("<xsd:HoraTransaccion>" + hour + "</xsd:HoraTransaccion>");
            builder.append("<xsd:SecuenciaTransaccion>" + sequence + "</xsd:SecuenciaTransaccion>");
            builder.append("<xsd:Terminal>" + Constants.IP_NUMBER_TEST + "</xsd:Terminal>");
            builder.append("<xsd:Tarjeta>" + numberCard + "</xsd:Tarjeta>");
            builder.append("<xsd:SubTipoTransaccion>" + Constants.NUMBER_SUB_TRANSACTION_BALANCE_INQUIRY_WITH_MOVEMENTS + "</xsd:SubTipoTransaccion>");
            builder.append("</xsd:Autorizar>");
            builder.append("</soapenv:Body>");
            builder.append("</soapenv:Envelope>");
            byte[] buffer = new byte[builder.toString().length()];
            buffer = builder.toString().getBytes();
            bout.write(buffer);
            byte[] b = bout.toByteArray();
            String SOAPAction = "Consultar";
            httpConn.setRequestProperty("Content-Length", String.valueOf(b.length));
            httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            httpConn.setRequestProperty("SOAPAction", SOAPAction);
            httpConn.setRequestMethod("POST");
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            OutputStream out = httpConn.getOutputStream();
            // Write the content of the request to the outputstream of the HTTP
            // Connection.
            out.write(b);
            out.close();
            // Ready with sending the request.
            // Read the response.
            InputStreamReader isr = new InputStreamReader(httpConn.getInputStream(), Charset.forName("UTF-8"));
            BufferedReader in = new BufferedReader(isr);
            // Write the SOAP message response to a String.

            while ((responseString = in.readLine()) != null) {
                outputString = outputString + responseString;
            }
            formattedSOAPResponse = formatXML(outputString);
            System.out.println(outputString);
            BalanceInquiryWithoutMovementsResponse balanceInquiryWithoutMovementsResponse = new BalanceInquiryWithoutMovementsResponse();
            try {
                balanceInquiryWithoutMovementsResponse.setCodigoError(getTagValue("ns:CodigoError", formattedSOAPResponse));
                balanceInquiryWithoutMovementsResponse.setMensajeError(getTagValue("ns:MensajeError", formattedSOAPResponse));
                balanceInquiryWithoutMovementsResponse.setCodigoRespuesta(getTagValue("ns:CodigoRespuesta", formattedSOAPResponse));
                balanceInquiryWithoutMovementsResponse.setMensajeRespuesta(getTagValue("ns:MensajeRespuesta", formattedSOAPResponse));
                balanceInquiryWithoutMovementsResponse.setCodigoAutorizacion(getTagValue("ns:CodigoAutorizacion", formattedSOAPResponse));
                balanceInquiryWithoutMovementsResponse.setDisponibleConsumos(getTagValue("ns:DisponibleConsumos", formattedSOAPResponse));
                balanceInquiryWithoutMovementsResponse.setDisponibleCuotas(getTagValue("ns:DisponibleCuotas", formattedSOAPResponse));
                balanceInquiryWithoutMovementsResponse.setDisponibleAdelantos(getTagValue("ns:DisponibleAdelantos", formattedSOAPResponse));
                balanceInquiryWithoutMovementsResponse.setDisponiblePrestamos(getTagValue("ns:DisponiblePrestamos", formattedSOAPResponse));
                balanceInquiryWithoutMovementsResponse.setSaldo(getTagValue("ns:Saldo", formattedSOAPResponse));
                balanceInquiryWithoutMovementsResponse.setSaldoEnDolares(getTagValue("ns:SaldoEnDolares", formattedSOAPResponse));
                balanceInquiryWithoutMovementsResponse.setPagoMinimo(getTagValue("ns:PagoMinimo", formattedSOAPResponse));            
                List<Movimiento> movimientos = new ArrayList<Movimiento>();
                for (Movimiento movimiento : movimientos) {
                    
                }
                 
            
            } catch (ArrayIndexOutOfBoundsException e) {
                balanceInquiryWithoutMovementsResponse.setCodigoError(getTagValue("ns:CodigoError", formattedSOAPResponse));
                balanceInquiryWithoutMovementsResponse.setMensajeError(getTagValue("ns:MensajeError", formattedSOAPResponse));
                balanceInquiryWithoutMovementsResponse.setCodigoRespuesta(getTagValue("ns:CodigoRespuesta", formattedSOAPResponse));
                balanceInquiryWithoutMovementsResponse.setMensajeRespuesta(getTagValue("ns:MensajeRespuesta", formattedSOAPResponse));

            }
            return null;
            //return new BalanceInquiryWithoutMovementsResponse(balanceInquiryWithoutMovementsResponse.getCodigoError(), balanceInquiryWithoutMovementsResponse.getMensajeError(), balanceInquiryWithoutMovementsResponse.getCodigoRespuesta(), balanceInquiryWithoutMovementsResponse.getMensajeRespuesta(), balanceInquiryWithoutMovementsResponse.getCodigoAutorizacion(),balanceInquiryWithoutMovementsResponse.getDisponibleConsumos(),balanceInquiryWithoutMovementsResponse.getDisponibleCuotas(),balanceInquiryWithoutMovementsResponse.getDisponibleAdelantos(),balanceInquiryWithoutMovementsResponse.getDisponiblePrestamos(),balanceInquiryWithoutMovementsResponse.getSaldo(), balanceInquiryWithoutMovementsResponse.getSaldoEnDolares(),balanceInquiryWithoutMovementsResponse.getPagoMinimo());

        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            throw new SocketTimeoutException();
        } catch (ConnectException ex) {
            ex.printStackTrace();
            throw new ConnectException();
        }catch (MalformedURLException ex) {
            ex.printStackTrace();
            throw new MalformedURLException();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new IOException();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception();
        }
    }
    
    
    public static String getTagValue(String tagName, String xml) {  
        return xml.split("<" + tagName + ">")[1].split("</" + tagName + ">")[0];
    }

    private static void ignoreSSL() {
        try {
            ////////////////////////////////////////////////////////////////
            //SE COLOCAR PARA IGNORAR SSL
            ///////////////////////////////////////////////////////////////
            XTrustProvider.install();
            final String TEST_URL = Constants.URL_TEST_WS;
            URL url = new URL(TEST_URL);
            HttpsURLConnection httpsCon = (HttpsURLConnection) url.openConnection();
            httpsCon.setConnectTimeout(CONNECTION_TIMEOUT);
            httpsCon.setHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            httpsCon.connect();
            InputStream is = httpsCon.getInputStream();
            int nread = 0;
            byte[] buf = new byte[8192];
            while ((nread = is.read(buf)) != -1) {
                //System.out.write(buf, 0, nread);
            }
            ////////////////////////////////////////////////////////////////
            //SE COLOCAR PARA IGNORAR SSL
            ///////////////////////////////////////////////////////////////
        } catch (MalformedURLException ex) {
            ex.printStackTrace();

        } catch (IOException ex) {
            ex.printStackTrace();

        }
    }

    // format the XML in pretty String
    private static String formatXML(String unformattedXml) {
        try {
            Document document = parseXmlFile(unformattedXml);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute("indent-number", 3);
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);
            StreamResult xmlOutput = new StreamResult(new StringWriter());
            transformer.transform(source, xmlOutput);
            return xmlOutput.getWriter().toString();
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }
    // parse XML

    private static Document parseXmlFile(String in) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(in));
            return db.parse(is);
        } catch (IOException | ParserConfigurationException | SAXException e) {
            throw new RuntimeException(e);
        }
    }
    
}
