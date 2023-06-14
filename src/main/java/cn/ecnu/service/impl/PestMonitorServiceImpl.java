package cn.ecnu.service.impl;

import cn.ecnu.enums.FilePathEnum;
import cn.ecnu.model.dto.ConditionDTO;
import cn.ecnu.model.dto.PestMonitorDTO;
import cn.ecnu.model.vo.PageResult;
import cn.ecnu.model.vo.PestMonitorVO;
import cn.ecnu.model.vo.PestRecognizeVO;
import cn.ecnu.strategy.context.UploadStrategyContext;
import cn.ecnu.utils.BeanCopyUtils;
import cn.ecnu.utils.EasyDLUtils;
import cn.ecnu.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ecnu.mapper.PestMonitorMapper;
import cn.ecnu.entity.PestMonitor;
import cn.ecnu.service.PestMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.text.DecimalFormat;
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

  @Autowired
  private UploadStrategyContext uploadStrategyContext;

  @Override
  public PestRecognizeVO recognize(String imageUrl) {
       String s = EasyDLUtils.easyDLImageClassify(imageUrl);
       String name = s.split(",")[1].split(":")[2].replace("\"","").replace("\"","");
       String score = s.split(",")[2].split(":")[1].replace("}]}","");
       BigDecimal bd = new BigDecimal(score);
       double accuracy = bd.setScale(3, BigDecimal.ROUND_DOWN).doubleValue() * 100;
       PestMonitor pestMonitor = new PestMonitor().builder().result(name).accuracy(accuracy).build();
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
        //根据传入的imageSrc来识别图片虫害信息
        PestRecognizeVO recognize = recognize(pestMonitor.getImageSrc());
        pestMonitor.setResult(recognize.getResult());
        pestMonitor.setAccuracy(recognize.getAccuracy());
        PestMonitor addPestMonitor = BeanCopyUtils.copyBean(pestMonitor, PestMonitor.class);
        baseMapper.insert(addPestMonitor);
    }

    @Override
    public void deletePestMonitor(List<Integer> pestMonitorIdList) {
      pestMonitorMapper.deleteBatchIds(pestMonitorIdList);
    }

    @Override
    public void updatePestMonitor(PestMonitorDTO pestMonitor) {
        //根据传入的imageSrc来识别图片虫害信息
        PestRecognizeVO recognize = recognize(pestMonitor.getImageSrc());
        pestMonitor.setResult(recognize.getResult());
        pestMonitor.setAccuracy(recognize.getAccuracy());
        PestMonitor updatePestMonitor = BeanCopyUtils.copyBean(pestMonitor, PestMonitor.class);
        baseMapper.updateById(updatePestMonitor);
    }

    @Override
    public String saveProductImages(MultipartFile file) {
        //上传文件
        String url = uploadStrategyContext.executeUploadStrategy(file, FilePathEnum.PEST.getFilePath());
        return url;
    }

}

