package com.chloxen95.RspamdConfiguration.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

public interface ConfigurationParser {
	
	/**
	 * ��ȡ������check_all_filters = false;</br>
	 * �������б�����ֵ���ַ����������䱣�浽Ŀ��Map��
	 * @param varStr Դ�ַ���
	 * @param result Ŀ��Map
	 */
	public void ReadVariable(String varStr, Map<String, Object> result);
	
	/**
	 * ��ȡ�б� classify_headers = [ "User-Agent", "X-Mailer", "Content-Type", "X-MimeOLE", ];</br>
	 * ���������б���ַ����������䱣�浽Ŀ��Map�С�</br>
	 * �б���ÿһ������У����򽫶�ȡΪһ���ַ���
	 * @param varNameStr Դ�ַ���
	 * @param br �ļ�������ʱӦ��ȡ���б��������ڵ���
	 * @param result Ŀ��Map
	 * @throws IOException 
	 */
	public void ReadList(String varNameStr, BufferedReader br, Map<String, Object> result) throws IOException;
	
	/**
	 * ��ȡӳ���ϵ�� dns { timeout = 1s; sockets = 16; retransmits = 5; }</br>
	 * ��������ӳ���ϵ���ַ����������䱣�浽Ŀ��Map�С�</br>
	 * ӳ���ϵ����У����򽫶�ȡΪһ���ַ���
	 * @param varNameStr Դ�ַ���
	 * @param br �ļ�������ʱӦ��ȡ��ӳ���ϵ�������ڵ���
	 * @param result Ŀ��Map
	 * @throws IOException
	 */
	public void ReadMap(String varNameStr, BufferedReader br, Map<String, Object> result) throws IOException;
	
	/**
	 * �����ַ����������ͣ����������䱣�浽Ŀ��Map�С�
	 * @param br �ļ���
	 * @param result Ŀ��Map
	 * @throws IOException
	 */
	public void ConfParser(BufferedReader br, Map<String, Object> result) throws IOException;
	
	/**
	 * ��ȡָ���ļ�����
	 * @param filePath �ļ�·��
	 * @return Map��ʽ���ļ�����
	 * @throws IOException
	 */
	public Map<String, Object> getFileContent(String path) throws IOException;

}
