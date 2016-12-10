package com.indicatorscanner.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import com.indicatorscanner.domain.CandlePattern;
import com.indicatorscanner.domain.CandleStick;
import com.indicatorscanner.domain.Indices;
import com.indicatorscanner.services.CandlePatternService;
import com.indicatorscanner.services.CandleStickService;
import com.indicatorscanner.services.IndexService;

@CrossOrigin(maxAge = 3600)
@Controller
public class ProductController {

    private CandleStickService candleStickService;
    private CandlePatternService candlePatternService;
    private IndexService indexService;

    @Autowired
    public ModelAndView setCandleStickService(
            CandleStickService candleStickService,
            CandlePatternService candlepatternService,
            IndexService indexService) {
        this.candleStickService = candleStickService;
        this.candlePatternService = candlepatternService;
        this.indexService = indexService;
        return null;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody Iterable<Indices> listIndex() {
        return indexService.listAllProducts();
    }


    @RequestMapping(value = "/candle", method = RequestMethod.GET)
    public @ResponseBody Iterable<CandleStick> listAllUsers() {
        return candleStickService.listAllProducts();
    }

    @RequestMapping(value = "/candle/{indexName}", method = RequestMethod.GET)
    public @ResponseBody Iterable<CandleStick> listCandleByIndex(
            @PathVariable("indexName") String indexName) {
        Indices index = indexService.findByIndexName(indexName);
        candleStickService.getCandleStickByIndex(index);
        return candleStickService.listAllProducts();
    }

    @RequestMapping(value = "/insertcandle", method = RequestMethod.POST)
    public ResponseEntity<CandleStick> insertcandle(
            @RequestBody String jsonStr) {
        CandleStick candleStick = new CandleStick();

        if (jsonStr != null) {
            final Gson gson = new GsonBuilder().create();
            candleStick = gson.fromJson(jsonStr, CandleStick.class);
            if (candleStick == null || candleStick.getPatternName() == null
                    || candleStick.getPatternName().getPatternID() == null
                    || "".equals(candleStick.getPatternName().getPatternID())) {
                return new ResponseEntity<CandleStick>(candleStick,
                        HttpStatus.NO_CONTENT);
            }
            CandlePattern patternName = candlePatternService
                    .saveOrUpdateCandlePattern(candleStick.getPatternName());
            candleStick.setPatternName(patternName);
            candleStickService.saveOrUpdateCandleStick(candleStick);
        } else {
            return new ResponseEntity<CandleStick>(candleStick,
                    HttpStatus.NO_CONTENT);
        }

        // TODO: call persistence layer to update
        return new ResponseEntity<CandleStick>(candleStick, HttpStatus.OK);
    }

    @RequestMapping(value = "/insertindices", method = RequestMethod.POST)
    public ResponseEntity<Indices> insertindices(
            @RequestBody String jsonStr) {
        Indices index = new Indices();
        if (jsonStr != null) {
            final RuntimeTypeAdapterFactory<Indices> typeFactory = RuntimeTypeAdapterFactory
                    .of(Indices.class, "type");
            final Gson gson = new GsonBuilder()
                    .registerTypeAdapterFactory(typeFactory).create();

            index = gson.fromJson(jsonStr, Indices.class);
            indexService.saveIndices(index);
        } else {
            return new ResponseEntity<Indices>(index, HttpStatus.NO_CONTENT);
        }

        // TODO: call persistence layer to update
        return new ResponseEntity<Indices>(index, HttpStatus.OK);
    }

    @RequestMapping(value = "/insertpattern", method = RequestMethod.POST)
    public ResponseEntity<CandlePattern> insertpattern(
            @RequestBody String jsonStr) {
        CandlePattern candlePattern = new CandlePattern();

        if (jsonStr != null) {
            final RuntimeTypeAdapterFactory<CandlePattern> typeFactory = RuntimeTypeAdapterFactory
                    .of(CandlePattern.class, "type");
            final Gson gson = new GsonBuilder()
                    .registerTypeAdapterFactory(typeFactory).create();

            candlePattern = gson.fromJson(jsonStr,
                    CandlePattern.class);
            candlePatternService.saveOrUpdateCandlePattern(candlePattern);
        } else {
            return new ResponseEntity<CandlePattern>(candlePattern,
                    HttpStatus.NO_CONTENT);
        }

        // TODO: call persistence layer to update
        return new ResponseEntity<CandlePattern>(candlePattern, HttpStatus.OK);
    }
}
