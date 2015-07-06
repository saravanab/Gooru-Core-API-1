/////////////////////////////////////////////////////////////
// TaxonomyService.java
// gooru-api
// Created by Gooru on 2014
// Copyright (c) 2014 Gooru. All rights reserved.
// http://www.goorulearning.org/
// Permission is hereby granted, free of charge, to any person obtaining
// a copy of this software and associated documentation files (the
// "Software"), to deal in the Software without restriction, including
// without limitation the rights to use, copy, modify, merge, publish,
// distribute, sublicense, and/or sell copies of the Software, and to
// permit persons to whom the Software is furnished to do so, subject to
// the following conditions:
// The above copyright notice and this permission notice shall be
// included in all copies or substantial portions of the Software.
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
// EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
// MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
// NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
// LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
// OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
// WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
/////////////////////////////////////////////////////////////
package org.ednovo.gooru.domain.service.taxonomy;

import java.util.List;
import java.util.Map;

import org.ednovo.gooru.core.api.model.Code;
import org.ednovo.gooru.core.api.model.CodeType;
import org.ednovo.gooru.core.api.model.TaxonomyDTO;
import org.ednovo.gooru.core.application.util.formatter.CodeFo;
import org.ednovo.gooru.core.application.util.formatter.FilterSubjectFo;
import org.json.JSONException;

public interface TaxonomyService {

	public static enum Taxonomy {
		SUBJECT("Subject"), COURSE("Course"), UNIT("Unit"), TOPIC("Topic"), LESSON("Lesson");

		private String label;

		private Taxonomy(String label) {
			this.label = label;
		}

		public String getLabel() {
			return this.label;
		}
	}

	Code findCodeByTaxCode(String taxonomyCode);

	List<Code> findRootTaxonomies(Short depth);

	int findMaxDepthInTaxonomy(Code code);

	CodeType findTaxonomyTypeBydepth(Code code, Short depth);

	List<CodeType> findTaxonomyLevels(Code root);

	List<CodeType> findAllTaxonomyLevels();

	List<Code> findChildTaxonomyCode(Integer codeId);

	List<Code> findChildTaxonomyCodeByOrder(Integer codeId, String order);

	List<Code> findCodeByType(Integer taxonomyLevel);

	List<Code> findParentTaxonomyCodes(Integer codeId, List<Code> codeList);

	List<Code> findSiblingTaxonomy(Code code);

	List<Code> findTaxonomyMappings(List<Code> codeList);

	String findRootLevelTaxonomy(Code code);

	void updateOrder(Code code);

	String makeTree(Code rootCode);

	void writeToDisk(Code cde) throws Exception;

	String findTaxonomyTree(String taxonomyCode, String format) throws Exception;

	void updateTaxonomyAssociation(Code taxonomy, List<Code> codes);

	void deleteTaxonomyMapping(Code code, List<Code> codes);

	Code findByLabel(String label);

	Code findByParent(String label, Integer parentId);

	List<Code> findChildTaxonomyCodeByDepth(Integer codeId, Integer depth);

	List<Code> findAll();

	List<Code> findAllByRoot(Integer codeId);

	List<Code> getCodesOfConent(Long contentId);

	Code findCodeByCodeId(Integer codeId);

	Code findCodeByCodeUId(String codeUId);

	TaxonomyDTO createTaxonomy(String parentCode, String label, String code, String order, String rootNodeId, String codeRoot, String displayCode) throws Exception;

	void writeTaxonomyToDisk();

	TaxonomyDTO updateTaxonomy(String codeId, String label, String order, String code) throws Exception;

	void deleteTaxonomy(String code) throws Exception;

	TaxonomyDTO createTaxonomyRoot(String code, String label, String isCodeAutoGenerated, Integer rootNodeId) throws Exception;

	TaxonomyDTO addLevel(String taxonomyCode, String label, String isCodeAutogenerated);

	TaxonomyDTO updateLevel(String leveldepth, String taxonomyCode, String label);

	void deleteLevel(String leveldepth, String taxonomyCode);

	TaxonomyDTO getTaxonomyLevels(String taxonomyCode);

	Code findCodeByTaxonomyCodeId(Integer taxonomyCodeId);

	Code findTaxonomyCodeById(Integer taxonomyCodeId);

	Map<String, List<Code>> findCodeByParentCodeId(String parentCodeId, boolean groupByCode, String creatorUid, String fetchType,String organizationCode);

	TaxonomyDTO updateAssocation(String taxonomyCodeId, String curriculumIds, Code code);

	TaxonomyDTO deleteMapping(String[] CurriculumId, Code code);

	Code saveUploadTaxonomyImage(Code code);

	List<Code> findParentTaxonomy(Integer codeId, boolean reverse);
	
	List<Code> getCurriculum();
	
	List<Map<String, Object>> getCurriculum(List<Integer> codeIds);

	List<CodeFo> getCourseBySubject(Integer codeId, Integer maxLessonLimit) throws JSONException;

	FilterSubjectFo getFilterSubject(Integer codeId, Integer maxLessonLimit);
	
	List<Map<String, Object>> getStandards(String code, Integer depth);	
	
	Map<String, Object> getTaxonomySkills();
	
}
