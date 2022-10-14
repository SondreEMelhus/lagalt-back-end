package com.lagaltBE.lagaltBE.algorithms;

/*
    Switch cases:

    1. Industry = 1
    2. Keyword = 2
    3. Skill = 4
    4. Industry, Keyword = 3
    5. Industry, Skill = 5
    6. Keyword, skill = 6
    7. Industry, Keyword, Skill = 7

    TODO: Legg til case dersom 'Alle industrier er valgt

    Industry = 1;
    Keyword = 2;
    Skill = 4;
*/

import com.lagaltBE.lagaltBE.models.Industry;
import com.lagaltBE.lagaltBE.models.Keyword;
import com.lagaltBE.lagaltBE.models.Project;
import com.lagaltBE.lagaltBE.models.Skill;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FilterAlgorithm {

    public Set<Project> filterProjects (Set<Project> projects, Industry selectedIndustry, Keyword selectedKeyword, Skill selectedSkill, Set<Project> unfilteredProjects) {

        int filterSelection = 0;
        List<Project> filteredProjects = new ArrayList<>();

        if (selectedIndustry.getTitle().equals("Industri") || selectedIndustry.getTitle().equals("Alle industrier")) { filterSelection += 1; }
        if (selectedKeyword.getTitle().equals("Nøkkelord")) { filterSelection += 2; }
        if (selectedSkill.getTitle().equals("Ferdighet")) { filterSelection += 4; }

        for (Project project : projects) {
            switch (filterSelection) {
                case 1:
                    if (project.getIndustry().getTitle().equals(selectedIndustry.getTitle())) {
                        filteredProjects.add(project);
                    }
                    break;
                case 2:
                    if (project.getKeywords().contains(selectedKeyword)) {
                        filteredProjects.add(project);
                    }
                    break;
                case 3:
                    if (project.getKeywords().contains(selectedKeyword) && project.getIndustry().getTitle().equals(selectedIndustry.getTitle())) {
                        filteredProjects.add(project);
                    }
                    break;
                case 4:
                    if (project.getSkills().contains(selectedSkill)) {
                        filteredProjects.add(project);
                    }
                    break;
                case 5:
                    if (project.getSkills().contains(selectedSkill) && project.getIndustry().getTitle().equals(selectedIndustry.getTitle())) {
                        filteredProjects.add(project);
                    }
                    break;
                case 6:
                    if (project.getKeywords().contains(selectedKeyword) && project.getSkills().contains(selectedSkill)) {
                        filteredProjects.add(project);
                    }
                    break;
                case 7:
                    if (project.getKeywords().contains(selectedKeyword) && project.getSkills().contains(selectedSkill)
                            && project.getIndustry().getTitle().equals(selectedIndustry.getTitle())) {
                        filteredProjects.add(project);
                    }
                    break;
                default:
                    return unfilteredProjects;
            }
        }

        Set<Project> filteredProjectsSet = new HashSet<>(filteredProjects);
        return filteredProjectsSet;
    }

}
