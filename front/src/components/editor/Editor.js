import { useRef, useEffect } from 'react';
import { Editor as Writer } from '@toast-ui/react-editor';
import prism from 'prismjs';
import 'prismjs/themes/prism.css';
import { fetchUploadImage } from '../../util/fetchFile';

import '@toast-ui/editor/dist/toastui-editor-viewer.css';
import '@toast-ui/editor/dist/toastui-editor.css';
import '@toast-ui/editor-plugin-code-syntax-highlight/dist/toastui-editor-plugin-code-syntax-highlight.css';
import codeSyntaxHighlight from '@toast-ui/editor-plugin-code-syntax-highlight';

export function Editor({
  onChange,
  height = '300px',
  isEditorClear,
  setIsEditorClear,
  isEditorEdit,
  setIsEditorEdit,
  initValue,
}) {
  const editorRef = useRef();

  const onChangeHandle = () => {
    const htmlElement = editorRef.current.getInstance().getHTML();
    const json = JSON.stringify(htmlElement);
    return onChange(json);
  };

  const onUploadImage = async (blob, callback) => {
    await fetchUploadImage(blob).then((path) => {
      console.log(path);
      callback(path, blob.name);
    });
    return false;
  };

  const onClearEditor = () => {
    editorRef.current.getInstance().setMarkdown('## *Your* **markdown** here');
  };

  const onChangeContent = () => {
    editorRef.current.getInstance().setHTML(initValue);
  };

  useEffect(() => {
    if (isEditorClear) {
      onClearEditor();
      setIsEditorClear(false);
    }

    if (isEditorEdit) {
      onChangeContent();
      setIsEditorEdit(false);
    }
  }, [isEditorClear, isEditorEdit]);

  return (
    <div className="mb-4 editor-wrapper">
      <Writer
        previewStyle="tab"
        height={height}
        initialEditType="markdown"
        initialValue="## *Your* **markdown** here"
        ref={editorRef}
        plugins={[[codeSyntaxHighlight, { highlighter: prism }]]}
        hideModeSwitch={true}
        onChange={onChangeHandle}
        useCommandShortcut={false}
        hooks={{
          addImageBlobHook: onUploadImage,
        }}
        toolbarItems={[
          ['heading', 'bold', 'strike'],
          ['hr', 'quote'],
          ['ul', 'ol', 'task'],
          ['table', 'image', 'link'],
          ['code', 'codeblock'],
        ]}
      />
    </div>
  );
}
