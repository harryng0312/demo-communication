package org.harryng.demo.controller.rs;

import lombok.RequiredArgsConstructor;
import org.harryng.demo.api.util.ResponseWrapper;
import org.harryng.demo.api.util.SessionHolder;
import org.harryng.demo.impl.asset.dto.AssetRes;
import org.harryng.demo.impl.asset.service.AssetService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Optional;

@RestController
@RequestMapping(value = "/asset", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AssetController {

    private final AssetService assetService;

//    @RequestMapping(value = "", method = RequestMethod.GET)
//    @ResponseBody
//    public String getById(SessionHolder sessionHolder, @RequestParam long id) throws Exception {
//        final Optional<AssetDto> assetDto = assetService.getById(sessionHolder, id, new LinkedHashMap<>());
//        final String result;
//        if (assetDto.isPresent()) {
//            result = TextUtil.objToJson(ResponseWrapper.<AssetDto>builder().data(assetDto.get()).build());
//        } else {
//            result = TextUtil.objToJson(ResponseWrapper.<AssetDto>builder().build());
//        }
//        return result;
//    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseWrapper<AssetRes> getById(SessionHolder sessionHolder, @RequestParam long id) throws Exception {
        final Optional<AssetRes> assetDto = assetService.getById(sessionHolder, id, new LinkedHashMap<>());
        final ResponseWrapper<AssetRes> result;
        if (assetDto.isPresent()) {
            result = ResponseWrapper.<AssetRes>builder().data(assetDto.get()).build();
        } else {
            result = ResponseWrapper.<AssetRes>builder().build();
        }
        return result;
    }

}
