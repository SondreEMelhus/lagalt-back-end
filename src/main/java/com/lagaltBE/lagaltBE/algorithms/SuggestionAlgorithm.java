package com.lagaltBE.lagaltBE.algorithms;

import com.lagaltBE.lagaltBE.models.*;

import java.util.*;

public class SuggestionAlgorithm {

    private List<HashMap<String, Integer>> generateScores (Account user, Set<Industry> industries, Set<Keyword> keywords, Set<Skill> skills ) {

        HashMap<String, Integer> industryMap = new HashMap<>();
        HashMap<String, Integer> keywordMap = new HashMap<>();
        HashMap<String, Integer> skillMap = new HashMap<>();

        //Populate hashMaps with values from industries, keywords and skills
        for (Industry industry : industries) {
            industryMap.put(industry.getTitle(), 0);
        }

        for (Keyword keyword : keywords) {
            if (!keywordMap.containsKey(keyword.getTitle())) {
                keywordMap.put(keyword.getTitle(), 0);
            }
        }

        for (Skill skill : skills) {
            if (!skillMap.containsKey(skill.getTitle())) {
                skillMap.put(skill.getTitle(), 0);
            }
        }

        for (ProjectInteractionHistory projectInteractionHistory : user.getProjectInteractionHistory()) {

            String projectIndustry = projectInteractionHistory.getProject().getIndustry().getTitle();

            //Add 3 score to the industry matching the industry of the project that is in the users viewHistory
            int industryScore = industryMap.get(projectIndustry);
            industryScore += 3;
            industryMap.replace(projectIndustry, industryScore);

            //Keyword viewhistory score
            for (Keyword keyword : projectInteractionHistory.getProject().getKeywords()) {

                int keywordScore = keywordMap.get(keyword.getTitle());
                keywordScore += 1;
                keywordMap.replace(keyword.getTitle(), keywordScore);
            }
            //Skill viewhistory score
            for (Skill skill : projectInteractionHistory.getProject().getSkills()) {
                if (user.getSkills().contains(skill)) {
                    int skillScore = skillMap.get(skill.getTitle());
                    skillScore += 2;
                    skillMap.replace(skill.getTitle(), skillScore);
                }
            }
        }

        for (Contributor projectContributionHistory : user.getContributors()) {

            //Industry
            String industryTitle = projectContributionHistory.getProject().getIndustry().getTitle();
            int industryScore = industryMap.get(industryTitle);
            industryScore += 5;
            industryMap.replace(industryTitle, industryScore);

            //Keyword
            for (Keyword keyword : projectContributionHistory.getProject().getKeywords()) {
                int keywordScore = keywordMap.get(keyword.getTitle());
                keywordScore += 2;
                keywordMap.replace(keyword.getTitle(), keywordScore);
            }

            //Skill
            for (Skill skill : projectContributionHistory.getProject().getSkills()) {
                if (user.getSkills().contains(skill)) {
                    int skillScore = skillMap.get(skill.getTitle());
                    skillScore += 4;
                    skillMap.replace(skill.getTitle(), skillScore);
                }
            }
        }

        List<HashMap<String, Integer>> listOfMaps = new ArrayList<>();
        listOfMaps.add(industryMap);
        listOfMaps.add(skillMap);
        listOfMaps.add(keywordMap);

        return listOfMaps;
    }


    //Method that scores each project based on a users skills, viewHistory and contribution history, and
    //returns a set of project in order of suggested interest
    public Set<Project> genereteSuggestions (Account user, Set<Project> projects, Set<Industry> industries,
                                           Set<Keyword> keywords, Set<Skill> skills) {

        List<HashMap<String, Integer>> scoreMaps = generateScores(user, industries, keywords, skills);

        HashMap<String, Integer> industryScores = scoreMaps.get(0);
        HashMap<String, Integer> keywordScores = scoreMaps.get(1);
        HashMap<String, Integer> skillScores = scoreMaps.get(2);

        HashMap<Project, Integer> projectScore = new HashMap<>();

        for (Project project : projects) {

            int score = 0;

            //Add the industry score to the projects overall score
            score += industryScores.get(project.getIndustry().getTitle());

            //Add each keyword score to the projects overall score
            for (Keyword keyword : project.getKeywords()) {
                score += keywordScores.get(keyword.getTitle());
            }

            //Add the skill score to the projects overall score
            for (Skill skill : project.getSkills()) {
                score += skillScores.get(skill.getTitle());
            }

            projectScore.put(project, score);
        }

        return sortProjects(projectScore);
    }

    //Method that sorts a hashMap in descending order based on entry value
    public Set<Project> sortProjects(HashMap<Project, Integer> hashMap) {

        LinkedHashMap<Project, Integer> sortedMap = new LinkedHashMap<>();
        ArrayList<Integer> list = new ArrayList<>();

        for (Map.Entry<Project, Integer> entry : hashMap.entrySet()) {
            list.add(entry.getValue());
        }
        Collections.sort(list);
        Collections.reverse(list);

        for (int num : list) {
            for (Map.Entry<Project, Integer> entry : hashMap.entrySet()) {
                if (entry.getValue().equals(num)) {
                    sortedMap.put(entry.getKey(), num);
                }
            }
        }

        Set<Project> sortedProjectSet = new HashSet<>(hashMap.keySet());
        return sortedProjectSet;

    }
}
