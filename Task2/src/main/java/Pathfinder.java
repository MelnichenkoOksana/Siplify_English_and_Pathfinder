import java.io.IOException;
import java.util.ArrayList;

class Point {
    public int Z;
    public int X;
    public int Y;
    public Point(int Z, int X,int Y)
    {
        this.Z=Z;
        this.X=X;
        this.Y=Y;
    }

}


class PathNode extends Point {
    public Point goal;
    public Point position;
    public PathNode(Point point, PathNode pathNode, Point goal) {
        super(point.Z, point.X, point.Y);
        position = point;
        setCameFrome(pathNode);
        this.goal = goal;
        if (pathNode!=null)//нужно только для стартовой точки
            setLengthFromStart(pathNode.getLengthFromStart());
        else setLengthFromStart(-1);
        setLengthToGoal(goal);
        setFullLength(getLengthFromStart()+getLengthToGoal());
    }

    public PathNode CameFrome;//родительская точка

    public void setCameFrome(PathNode CameFrome) {
        this.CameFrome = CameFrome;
    }

    public int LengthFromStart;

    public void setLengthFromStart(int i) {
        LengthFromStart = i + 1;
    }

    public int getLengthFromStart() {
        return LengthFromStart;
    }

    public int LengthToGoal;
    public void setLengthToGoal(Point goal)//манхэттенское расстояние до цели
    {
        LengthToGoal =Math.abs(goal.Z - this.position.Z) + Math.abs(goal.X - this.position.X) + Math.abs(goal.Y - this.position.Y);
    }
    public int getLengthToGoal()
    {
        return LengthToGoal;
    }

    public int FullLength;
    public void setFullLength(int i)
    {
        this.FullLength=i;
    }
    public int getFullLength() {
        return FullLength;
    }

}

class newSolution {
    public static char[][][] matrix;
    public static void main(String[] args) throws IOException{
        matrix = InputOutput.conversionStringAnArray(InputOutput.getInfoFromFile());
        Point start = InputOutput.searchDesiredPoint(matrix, '1'); // координаты принца
        Point goal =  InputOutput.searchDesiredPoint(matrix, '2');// координаты принцессы
        ArrayList<Point> list = FindPath(start,goal);

        for (Point point : list)
        {
            System.out.print(point.Z + " "+ point.X + " " + point.Y + " , ");
        }
        System.out.println();
        System.out.println(list.size()-1);

    }

    public static ArrayList<Point> FindPath(Point start, Point goal)
    {
        ArrayList<PathNode> closedSet = new ArrayList<>();//список рассмотренных точек
        ArrayList<PathNode> openSet = new ArrayList<>();//список еще не рассмотренных точек

        PathNode startNode = new PathNode(start,null,goal);

        startNode.LengthFromStart=0;
        openSet.add(startNode);
        while (openSet.size() > 0)
        {
            PathNode currentNode = minLength(openSet);//находит точку с min "Полным путем"
            if (currentNode.position.Z == goal.Z &&currentNode.position.X == goal.X && currentNode.position.Y == goal.Y)
                return GetPath(currentNode);//если в конечной точке, то возращает весь путь
            closedSet.add(currentNode);
            openSet.remove(currentNode);

            for (PathNode neighbourNode : GetNeighbours(currentNode, goal))//рассматривает соседей текущей точки
            {
                if (CountInList(closedSet,neighbourNode)>0)//проверяет есть ли точка с этими координатами в closedSet
                    continue;//если да, то берем следующую
                PathNode openNode = getInList(openSet, neighbourNode);//ищет точку с такими же координатами в openSet
                if (openNode == null) {//если нет, то добавляет
                    openSet.add(neighbourNode);
                } else {//если есть, то проверяет не большее ли у нее путь от старта, чем у соседа текущей точки
                    if (openNode.LengthFromStart > neighbourNode.LengthFromStart) {
                        //если больше, то заменяем ее данные на данные соседа
                        openNode.setCameFrome(currentNode);
                        openNode.LengthFromStart = neighbourNode.LengthFromStart;
                        openNode.FullLength = neighbourNode.FullLength;
                    }
                }
            }
        }
        return null;
    }
    private static ArrayList<PathNode> GetNeighbours(PathNode pathNode, Point goal)//возвращает список "годных" соседей
    {
        ArrayList<PathNode> result = new ArrayList<>();
        Point[] neighbourPoints = new Point[5];
        neighbourPoints[0] = new Point(pathNode.position.Z,pathNode.position.X + 1, pathNode.position.Y);
        neighbourPoints[1] = new Point(pathNode.position.Z, pathNode.position.X - 1, pathNode.position.Y);
        neighbourPoints[2] = new Point(pathNode.position.Z, pathNode.position.X, pathNode.position.Y + 1);
        neighbourPoints[3] = new Point(pathNode.position.Z, pathNode.position.X, pathNode.position.Y - 1);
        neighbourPoints[4] = new Point(pathNode.position.Z-1, pathNode.position.X, pathNode.position.Y);

        for(Point point : neighbourPoints)
            {
            if (point.Z < 0 || point.Z >= matrix.length)
                continue;
            if (point.X < 0 || point.X >= matrix[0].length)
                continue;
            if (point.Y < 0 || point.Y >= matrix[0][0].length)
                continue;
            if (matrix[point.Z][point.X][point.Y] == 'o')
                continue;

            PathNode neighbourNode = new PathNode(point,pathNode,goal);
            result.add(neighbourNode);
        }
        return result;
    }
    public static int CountInList (ArrayList<PathNode> list, PathNode pathNode)//проверяет есть ли точка с этими координатами в closedSet
    {

        for (PathNode point : list)
        {
            if (point.position.X == pathNode.position.X && point.position.Y==pathNode.position.Y)
            {
                return 1;
            }
        }
        return 0;
    }
    public static PathNode getInList (ArrayList<PathNode> list,PathNode pathNode)//ищет точку с такими же координатами в openSet
    {
        for (PathNode point : list)
        {
            if (point.position.X == pathNode.position.X && point.position.Y==pathNode.position.Y) {

                return point;
            }
        }
        return null;
    }

    public static ArrayList<Point> GetPath (PathNode pathNode)//возвращает список точек до данной.
    {
        ArrayList<Point> result = new ArrayList<>();
        PathNode current = pathNode;
        while (current!=null)
        {
            result.add(new Point(current.Z, current.X,current.Y));
            current = current.CameFrome;
        }
        return result;
    }


    public static PathNode minLength (ArrayList<PathNode> set)//находит точку с min "Полным путем"
    {
        PathNode result = set.get(0);
        for (PathNode min : set)
        {
            if (min.FullLength<result.getFullLength())
            {
                result = min;
            }
        }
        return result;
    }
}