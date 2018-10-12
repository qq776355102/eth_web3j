package eth_web3j;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.script.*;


public class Web3js {

	
	/**
	 * @param args
	 * @throws FileNotFoundException
	 * @throws ScriptException
	 * @throws NoSuchMethodException
	 */
	public static void main(String[] args) throws FileNotFoundException, ScriptException, NoSuchMethodException {
		
//		E:\web3\web3.min.js
	     /*mimeType为传输的文件类型,如 application/javascript*/
        /*获取执行JavaScript的执行引擎*/
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
        /*为文件注入全局变量*/
        Bindings bindings = engine.createBindings();
        bindings.put("factor", 2);
        /*设置绑定参数的作用域*/
        engine.setBindings(bindings,ScriptContext.ENGINE_SCOPE);
        Object eval = engine.eval(new FileReader("E:\\web3\\web3.min.js"));
        System.err.println(eval);
//        engine.eval("Web3()");
//        engine.eval("function instance () {return Web3; }");
//        Object eval2 = engine.eval("function instance2 () {return instance() + 4; }");
//        Invocable invocable = (Invocable) engine;
//        Object invokeFunction = invocable.invokeFunction("instance");
//        System.err.println(invokeFunction.toString());
//        Object invokeMethod = invocable.invokeMethod(eval, "new");
        
        
        
	}
	
}
