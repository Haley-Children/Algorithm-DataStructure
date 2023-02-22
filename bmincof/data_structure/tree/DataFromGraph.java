package structure.tree;

import java.util.HashMap;
import java.util.LinkedList;

// 특정 프로젝트가 완료되어야만 진행할 수 있는 프로젝트가 있을 때
// 각 프로젝트의 의존 여부를 전달받으면 의존도에 입각한 프로젝트의 진행순서를 반환하는 프로그램을 작성
public class DataFromGraph {
    static class Project {
        private String name;
        // 그래프를 표현하기 위한 리스트
        private LinkedList<Project> dependencies;
        private boolean marked;
        public Project(String name) {
            this.name = name;
            this.marked = false;
            this.dependencies = new LinkedList<>();
        }

        public void addDependency(Project project) {
            if(!dependencies.contains(project)) {
                dependencies.add(project);
            }
        }

        public LinkedList<Project> getDependencies() {
            return dependencies;
        }

        public String getName() {
            return name;
        }

        public void setMarked(boolean marked) {
            this.marked = marked;
        }

        public boolean getMarked() {
            return marked;
        }
    }

    static class ProjectManager {
        private HashMap<String, Project> projects;
        public ProjectManager(String[] names, String[][] dependencies) {
            buildProjects(names);
            addDependencies(dependencies);
        }

        public void buildProjects(String[] names) {
            projects = new HashMap<>();
            for (int i = 0; i < names.length; i++) {
                projects.put(names[i], new Project(names[i]));
            }
        }

        public void addDependencies(String[][] dependencies) {
            for(String[] dependency : dependencies) {
                Project before = findProject(dependency[0]);
                Project after = findProject(dependency[1]);
                after.addDependency(before);
            }
        }

        private int index;
        public Project[] buildOrder() {
            initMarkingFlages();
            Project[] ordered = new Project[this.projects.size()];
            index = 0;
            for(Project project : this.projects.values()) {
                buildOrder(project, ordered);
            }
            return ordered;
        }

        // 구현 핵심부
        public void buildOrder(Project project, Project[] ordered) {
            // 해당 프로젝트를 하기 전에 먼저 진행해야 할 프로젝트가 있으면
            // 선행 프로젝트를 먼저 수행하도록 buildOrder 호출
            if(!project.getDependencies().isEmpty()) {
                for(Project p : project.getDependencies()) {
                    buildOrder(p, ordered);
                }
            }
            // 해당 프로젝트를 먼저 수행했다면 스킵
            if(!project.getMarked()) {
                project.setMarked(true);
                ordered[index] = project;
                index++;
            }
        }

        private void initMarkingFlages() {
            for(Project project : this.projects.values()) {
                project.setMarked(false);
            }
        }

        public Project findProject(String name) {
            return projects.get(name);
        }
    }

    public static void main(String[] args) {
        // projects : a, b, c, d, e, f, g
        String[] projects = {"a", "b", "c", "d", "e", "f", "g"};
        // dependencies : (f, a), (f, b), (f, c), (b, a), (c, a), (a, e), (b, e), (d, g)
        String[][] dependencies = {{"f", "a"}, {"f", "b"}, {"f", "c"}, {"b", "a"},
                {"c", "a"}, {"a", "e"}, {"b", "e"}, {"d", "g"}};
        ProjectManager pm = new ProjectManager(projects, dependencies);
        Project[] ps = pm.buildOrder();

        for(Project p : ps) {
            if(p != null) {
                System.out.print(p.getName() + " ");
            }
        }
    }
}
