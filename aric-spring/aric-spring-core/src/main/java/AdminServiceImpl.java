import com.aricframework.core.base.impl.BaseServiceImpl;
import com.aricframework.core.domain.Order;
import com.aricframework.core.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService {

    @Override
    public Page<Admin> selectPage(Map<String, Object> searchParams, List<Order> orders, Page pageable) {
        return super.selectPage(searchParams, orders, pageable);
    }
}