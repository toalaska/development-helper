package cn.toalaska.development;

import cn.toalaska.development.ui.DTODialog;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;
import org.apache.http.util.TextUtils;

public class DTOAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent event) {

//        alertSelectText(editor);

//        test1(e, editor);

        DTODialog dtoDialog = new DTODialog();
        dtoDialog.setVisible(true);
//        addMethod(event);

    }

    private void addMethod(AnActionEvent event) {
        PsiFile psiFile = event.getData(LangDataKeys.PSI_FILE);
        if (psiFile == null) return;
        WriteCommandAction.runWriteCommandAction(event.getProject(), () -> {
            Editor editor = event.getData(PlatformDataKeys.EDITOR);
            if (editor == null) return;
            Project project = editor.getProject();
            if (project == null) return;

            PsiElement element = psiFile.findElementAt(editor.getCaretModel().getOffset());
            PsiClass psiClass = PsiTreeUtil.getParentOfType(element, PsiClass.class);
            if (psiClass == null) return;

            if (psiClass.getNameIdentifier() == null) return;
            String className = psiClass.getNameIdentifier().getText();

            PsiElementFactory elementFactory = JavaPsiFacade.getElementFactory(project);
            String methodText = buildMethodText(className);
            PsiMethod psiMethod = elementFactory.createMethodFromText(methodText, psiClass);
            psiClass.add(psiMethod);

        });
    }


    private boolean alertSelectText(Editor editor) {
        if (null == editor) {
            return true;
        }
        SelectionModel model = editor.getSelectionModel();
        final String selectedText = model.getSelectedText();
        if (TextUtils.isEmpty(selectedText)) {
            return true;
        }
        Messages.showMessageDialog(selectedText, "Information", Messages.getInformationIcon());
        return false;
    }

    private String buildMethodText (String className){
        return "public static void getInstance() {\n" +
                "       \n" +
                "    }";
    }


}
