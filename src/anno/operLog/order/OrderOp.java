package anno.operLog.order;

import anno.operLog.OpLog;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

public class OrderOp {
    //有了上面的切面及注解后，我们只就要在对应的方法上增加注解标注即可
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    //入参的参数列表中已经有来了被操作的对象的唯一性标识，直接使用#id即可
    @OpLog(opType = OpLog.OpType.QUERY, opItem = "order", opItemIdExpression = "#id")
    public @ResponseBody
    HashMap view(@RequestParam(name = "id") String id) throws Exception {
        return null;
    }

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    @OpLog(opType = OpLog.OpType.UPDATE, opItem = "order", opItemIdExpression = "#orderVo.id")
    public @ResponseBody
    HashMap update(OrderVO orderVO) {
        return null;
    }

    //
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    @OpLog(opType = OpLog.OpType.INSERT, opItem = "order", opItemIdExpression = "#insertResult.id")
    public @ResponseBody
    HashMap insert(OrderVO orderVO) {
        return null;
    }
}


