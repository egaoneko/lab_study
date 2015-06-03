package net.study.service.sequence;

import net.study.domain.Sequence;
import net.study.repository.SequenceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/2/15 | 10:01 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

@Service
public class SequenceServiceImpl implements SequenceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SequenceServiceImpl.class);
    private final SequenceRepository sequenceRepository;

    @Autowired
    public SequenceServiceImpl(SequenceRepository sequenceRepository) {
        this.sequenceRepository = sequenceRepository;
    }

    @Override
    public long generateNextGroupNumber(String name) {
        Sequence sequence = sequenceRepository.findOneByName(name);
        sequence.setNextValue(sequence.getNextValue()+1);
        sequenceRepository.save(sequence);
        LOGGER.debug("Generate {} Next Group Number : {}, {}", name, sequence.getNextValue()-1, sequence.getNextValue());
        return sequence.getNextValue();
    }
}
