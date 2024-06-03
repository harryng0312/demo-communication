package org.harryng.demo.controller.rs;

import lombok.RequiredArgsConstructor;
import org.harryng.demo.api.util.ResponseWrapper;
import org.harryng.demo.api.util.SessionHolder;
import org.harryng.demo.api.util.TextUtil;
import org.harryng.demo.impl.asset.dto.AssetDto;
import org.harryng.demo.impl.asset.service.AssetService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.LinkedHashMap;
import java.util.Optional;

@RestController
@RequestMapping("/asset")
@RequiredArgsConstructor
public class AssetController {

    private final AssetService assetService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseWrapper<AssetDto> getById(SessionHolder sessionHolder, @RequestParam long id) throws Exception {
        final Optional<AssetDto> assetDto = assetService.getById(sessionHolder, id, new LinkedHashMap<>());
        final ResponseWrapper<AssetDto> result;
        if (assetDto.isPresent()) {
            result = ResponseWrapper.<AssetDto>builder().data(assetDto.get()).build();
        } else {
            result = ResponseWrapper.<AssetDto>builder().build();
        }
        return result;
    }
}
