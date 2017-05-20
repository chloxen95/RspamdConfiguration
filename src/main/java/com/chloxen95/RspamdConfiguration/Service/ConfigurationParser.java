package com.chloxen95.RspamdConfiguration.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

public interface ConfigurationParser {
	
	/**
	 * 读取变量：check_all_filters = false;</br>
	 * 解析带有变量赋值的字符串，并将其保存到目标Map中
	 * @param varStr 源字符串
	 * @param result 目标Map
	 */
	public void ReadVariable(String varStr, Map<String, Object> result);
	
	/**
	 * 读取列表： classify_headers = [ "User-Agent", "X-Mailer", "Content-Type", "X-MimeOLE", ];</br>
	 * 解析带有列表的字符串，并将其保存到目标Map中。</br>
	 * 列表中每一项需分行，否则将读取为一般字符串
	 * @param varNameStr 源字符串
	 * @param br 文件流，此时应读取到列表名称所在的行
	 * @param result 目标Map
	 * @throws IOException 
	 */
	public void ReadList(String varNameStr, BufferedReader br, Map<String, Object> result) throws IOException;
	
	/**
	 * 读取映射关系： dns { timeout = 1s; sockets = 16; retransmits = 5; }</br>
	 * 解析带有映射关系的字符串，并将其保存到目标Map中。</br>
	 * 映射关系需分行，否则将读取为一般字符串
	 * @param varNameStr 源字符串
	 * @param br 文件流，此时应读取到映射关系名称所在的行
	 * @param result 目标Map
	 * @throws IOException
	 */
	public void ReadMap(String varNameStr, BufferedReader br, Map<String, Object> result) throws IOException;
	
	/**
	 * 分析字符串所属类型，并将并将其保存到目标Map中。
	 * @param br 文件流
	 * @param result 目标Map
	 * @throws IOException
	 */
	public void ConfParser(BufferedReader br, Map<String, Object> result) throws IOException;
	
	/**
	 * 获取指定文件内容
	 * @param filePath 文件路径
	 * @return Map格式的文件内容
	 * @throws IOException
	 */
	public Map<String, Object> getFileContent(String path) throws IOException;

}
