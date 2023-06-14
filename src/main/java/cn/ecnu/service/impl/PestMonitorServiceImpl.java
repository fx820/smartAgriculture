package cn.ecnu.service.impl;

import cn.ecnu.model.dto.ConditionDTO;
import cn.ecnu.model.dto.PestMonitorDTO;
import cn.ecnu.model.vo.PageResult;
import cn.ecnu.model.vo.PestMonitorVO;
import cn.ecnu.model.vo.PestRecognizeVO;
import cn.ecnu.utils.BeanCopyUtils;
import cn.ecnu.utils.EasyDLUtils;
import cn.ecnu.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ecnu.mapper.PestMonitorMapper;
import cn.ecnu.entity.PestMonitor;
import cn.ecnu.service.PestMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @intro: PestMonitor表服务实现类
 * @author: zachary
 * @version: 1.0
 */
 
@Service("pestMonitorService")
public class PestMonitorServiceImpl extends ServiceImpl<PestMonitorMapper, PestMonitor> implements PestMonitorService {

  @Autowired
  private PestMonitorMapper pestMonitorMapper;

  @Override
  public PestRecognizeVO recognize(String imageUrl) {
       String s = EasyDLUtils.easyDLImageClassify(imageUrl);
       String result = s.split(",")[1];
       String name = result.split(",")[0];
       String score = result.split(",")[1];
       PestMonitor pestMonitor = new PestMonitor().builder().result(name).accuracy(score).build();
       System.out.println(pestMonitor);
       PestRecognizeVO recognizeVO = BeanCopyUtils.copyBean(pestMonitor, PestRecognizeVO.class);
       return recognizeVO;
  }

    @Override
    public PageResult<PestMonitorVO> listPestMonitorVO(ConditionDTO condition) {
      Long count = pestMonitorMapper.countPestMonitor(condition);
      if (count==0){
          return new PageResult<>();
      }
      List<PestMonitorVO> pestMonitorVOS = pestMonitorMapper.selectPestMonitorVO(PageUtils.getLimit(),PageUtils.getSize(),condition);
      return new PageResult<>(pestMonitorVOS,count);
    }

    @Override
    public void addPestMonitor(PestMonitorDTO pestMonitor) {
        PestMonitor addPestMonitor = BeanCopyUtils.copyBean(pestMonitor, PestMonitor.class);
        baseMapper.insert(addPestMonitor);
    }

    @Override
    public void deletePestMonitor(List<Integer> pestMonitorIdList) {
      pestMonitorMapper.deleteBatchIds(pestMonitorIdList);
    }

    @Override
    public void updatePestMonitor(PestMonitorDTO pestMonitor) {
        PestMonitor updatePestMonitor = BeanCopyUtils.copyBean(pestMonitor, PestMonitor.class);
        baseMapper.updateById(updatePestMonitor);
    }


}

